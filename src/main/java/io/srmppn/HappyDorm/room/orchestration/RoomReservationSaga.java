package io.srmppn.HappyDorm.room.orchestration;

import io.srmppn.HappyDorm.account.api.RoomAssign.AssignRoomCommand;
import io.srmppn.HappyDorm.account.api.RoomAssign.UnAssignRoomCommand;
import io.srmppn.HappyDorm.account.command.RoomAlreadyAssignedException;
import io.srmppn.HappyDorm.billing.api.BillCancellation.*;
import io.srmppn.HappyDorm.billing.api.BillingCreation.CreateBillCommand;
import io.srmppn.HappyDorm.billing.api.PaymentVerification.*;
import io.srmppn.HappyDorm.billing.command.ReservationSummary;
import io.srmppn.HappyDorm.room.api.RoomPaymentScheduling.RoomRentedEvent;
import io.srmppn.HappyDorm.room.api.RoomReservation.RoomReservationExpiredEvent;
import io.srmppn.HappyDorm.room.api.RoomReservation.RoomReservedEvent;
import io.srmppn.HappyDorm.room.api.RoomReservation.RoomUnreservedEvent;
import io.srmppn.HappyDorm.room.api.RoomReservation.UnreserveRoomCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.GenericEventMessage;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import java.time.Duration;
import java.util.UUID;

@Saga
public class RoomReservationSaga {

    private final static Duration reservationTime = Duration.ofDays(1);
    public final static String associated = "roomId";

    @Autowired
    @Transient
    private CommandGateway commandGateway;

    @Autowired
    @Transient
    private EventBus eventBus;

    @Autowired
    @Transient
    private EventScheduler eventScheduler;

    private UUID accountId;
    private UUID billId;

    @StartSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(RoomReservedEvent event) {
        try {
            accountId = event.getAccountId();
            commandGateway.sendAndWait(new AssignRoomCommand(event.getAccountId(),
                                                             event.getRoomId()));
            billId = UUID.randomUUID();
            commandGateway.sendAndWait(new CreateBillCommand<>(billId,
                                                               event.getRoomId(),
                                                               createReservationBill()));

            eventScheduler.schedule(reservationTime, new RoomReservationExpiredEvent(event.getRoomId()));
        } catch (RoomAlreadyAssignedException error) {
            commandGateway.sendAndWait(new UnreserveRoomCommand(event.getRoomId()));
        }
    }

    @SagaEventHandler(associationProperty = associated)
    public void on(RoomReservationExpiredEvent event) {
        commandGateway.sendAndWait(new UnAssignRoomCommand(accountId));
        commandGateway.sendAndWait(new UnreserveRoomCommand(event.getRoomId()));

        commandGateway.sendAndWait(new CancelBillCommand(billId));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(PaymentVerifiedEvent event) {
        eventBus.publish(GenericEventMessage.asEventMessage(new RoomRentedEvent(event.getRoomId())));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(RoomUnreservedEvent event) {

    }

    private ReservationSummary createReservationBill() {
        return new ReservationSummary();
    }
}
