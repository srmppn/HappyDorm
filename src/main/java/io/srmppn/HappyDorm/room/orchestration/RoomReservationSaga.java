package io.srmppn.HappyDorm.room.orchestration;

import io.srmppn.HappyDorm.account.api.RoomAssign.*;
import io.srmppn.HappyDorm.account.command.RoomAlreadyAssignException;
import io.srmppn.HappyDorm.billing.api.BillPayment.*;
import io.srmppn.HappyDorm.billing.api.BillingCreation.CreateBillCommand;
import io.srmppn.HappyDorm.billing.command.ReservationSummary;
import io.srmppn.HappyDorm.room.api.RoomReservation.RoomReservedEvent;
import io.srmppn.HappyDorm.room.api.RoomReservation.RoomUnreservedEvent;
import io.srmppn.HappyDorm.room.api.RoomReservation.UnreserveRoomCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import java.util.UUID;

@Saga
public class RoomReservationSaga {

    public final static String associated = "roomId";

    @Autowired
    @Transient
    private CommandGateway commandGateway;

    private UUID accountId;

    @StartSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(RoomReservedEvent event) {
        try {
            accountId = event.getAccountId();
            commandGateway.sendAndWait(new AssignRoomCommand(event.getAccountId(),
                                                             event.getRoomId()));

            commandGateway.sendAndWait(new CreateBillCommand<ReservationSummary>(UUID.randomUUID(),
                                                                                 event.getRoomId(),
                                                                                 createReservationBill()));
        } catch (RoomAlreadyAssignException error) {
            commandGateway.sendAndWait(new UnreserveRoomCommand(event.getRoomId()));
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(BillPaidEvent event) {

    }

    @EndSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(BillExpiredEvent event) {
        commandGateway.sendAndWait(new UnAssignRoomCommand(accountId));
        commandGateway.sendAndWait(new UnreserveRoomCommand(event.getRoomId()));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = associated)
    public void on(RoomUnreservedEvent event) {

    }

    private ReservationSummary createReservationBill() {
        return new ReservationSummary();
    }
}
