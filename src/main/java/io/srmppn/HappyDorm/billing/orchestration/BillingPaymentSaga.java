package io.srmppn.HappyDorm.billing.orchestration;

import io.srmppn.HappyDorm.billing.api.BillingCreation.BillCreatedEvent;
import io.srmppn.HappyDorm.billing.api.BillingCreation.BillExpiredEvent;
import io.srmppn.HappyDorm.payment.api.TransactionCreation.*;
import io.srmppn.HappyDorm.payment.api.TransactionVerification.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import java.util.UUID;

@Saga
public class BillingPaymentSaga {

    public final static String billId = "billId";

    @Autowired
    @Transient
    private EventScheduler eventScheduler;

    @Autowired
    @Transient
    private CommandGateway commandGateway;

    private ScheduleToken scheduleToken;

    @StartSaga
    @SagaEventHandler(associationProperty = billId)
    public void on(BillCreatedEvent event) {
        commandGateway.send(new CreateTransactionCommand(UUID.randomUUID().toString(),
                                                         event.getBillId(),
                                                         event.getPaymentAmount()));
        scheduleToken = eventScheduler.schedule(event.getExpiry(),
                                                new BillExpiredEvent(event.getBillId(),
                                                                     event.getRoomId()));
    }

    @SagaEventHandler(associationProperty = billId)
    public void on(TransactionVerifiedEvent event) {
        eventScheduler.cancelSchedule(scheduleToken);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = billId)
    public void on(BillExpiredEvent event) {
    }
}
