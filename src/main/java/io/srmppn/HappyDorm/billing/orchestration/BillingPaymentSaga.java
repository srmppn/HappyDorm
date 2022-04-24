package io.srmppn.HappyDorm.billing.orchestration;

import io.srmppn.HappyDorm.billing.api.BillPayment.BillExpiredEvent;
import io.srmppn.HappyDorm.billing.api.BillPayment.BillPaidEvent;
import io.srmppn.HappyDorm.billing.api.BillingCreation.BillCreatedEvent;
import io.srmppn.HappyDorm.payment.api.TransactionCancellation.CancelTransactionCommand;
import io.srmppn.HappyDorm.payment.api.TransactionCreation.CreateTransactionCommand;
import io.srmppn.HappyDorm.payment.api.TransactionVerification.TransactionVerifiedEvent;
import io.srmppn.HappyDorm.payment.service.InvalidPaymentException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.GenericEventMessage;
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

    public final static String associated = "billId";

    @Autowired
    @Transient
    private EventScheduler eventScheduler;

    @Autowired
    @Transient
    private CommandGateway commandGateway;

    @Autowired
    @Transient
    private EventBus eventBus;

    private String transactionId;
    private UUID roomId;
    private ScheduleToken scheduleToken;

    @StartSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(BillCreatedEvent<?> event) {
        roomId = event.getRoomId();
        try {
            transactionId = commandGateway.sendAndWait(new CreateTransactionCommand(UUID.randomUUID(),
                                                                                    event.getBillId(),
                                                                                    event.getSummary()
                                                                                         .totalCost()));
            scheduleToken = eventScheduler.schedule(event.getExpiry(),
                                                    new BillExpiredEvent(event.getBillId(),
                                                                         event.getRoomId()));
        } catch (InvalidPaymentException error) {
            // do what ?
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(TransactionVerifiedEvent event) {
        eventScheduler.cancelSchedule(scheduleToken);
        eventBus.publish(GenericEventMessage.asEventMessage(new BillPaidEvent(event.getBillId(),
                                                                              roomId)));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(BillExpiredEvent event) {
        commandGateway.send(new CancelTransactionCommand(transactionId));
    }
}
