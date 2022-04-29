package io.srmppn.happydorm.room.orchestration;

import io.srmppn.happydorm.account.api.RoomAssignedEvent;
import io.srmppn.happydorm.account.api.RoomUnAssignedEvent;
import io.srmppn.happydorm.account.api.UnAssignRoomCommand;
import io.srmppn.happydorm.account.command.RoomAlreadyAssignedException;
import io.srmppn.happydorm.billing.api.CancelBillCommand;
import io.srmppn.happydorm.billing.api.CreateBillCommand;
import io.srmppn.happydorm.billing.api.PaymentVerifiedEvent;
import io.srmppn.happydorm.billing.command.ReservationSummary;
import io.srmppn.happydorm.room.api.ReserveRoomCommand;
import io.srmppn.happydorm.room.api.RoomRentedEvent;
import io.srmppn.happydorm.room.api.RoomReservationExpiredEvent;
import io.srmppn.happydorm.room.api.UnreserveRoomCommand;
import io.srmppn.happydorm.room.command.RoomAlreadyReservedException;
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
    public void on(RoomAssignedEvent event) {
        try {
            accountId = event.getAccountId();
            commandGateway.sendAndWait(new ReserveRoomCommand(event.getAccountId(),
                                                              event.getRoomId()));
            billId = UUID.randomUUID();
            commandGateway.sendAndWait(new CreateBillCommand<>(billId,
                                                               event.getRoomId(),
                                                               createReservationBill()));

            eventScheduler.schedule(reservationTime, new RoomReservationExpiredEvent(event.getRoomId()));
        } catch (RoomAlreadyReservedException error) {
            commandGateway.sendAndWait(new UnAssignRoomCommand(accountId));
        }
    }

    @SagaEventHandler(associationProperty = associated)
    public void on(RoomReservationExpiredEvent event) {
        commandGateway.sendAndWait(new CancelBillCommand(billId));
        commandGateway.sendAndWait(new UnreserveRoomCommand(event.getRoomId()));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(PaymentVerifiedEvent event) {
        eventBus.publish(GenericEventMessage.asEventMessage(
                new RoomRentedEvent(event.getRoomId())));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(RoomUnAssignedEvent event) {

    }

    private ReservationSummary createReservationBill() {
        return new ReservationSummary();
    }
}

