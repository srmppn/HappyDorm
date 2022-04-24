package io.srmppn.HappyDorm.room.command;

import io.srmppn.HappyDorm.room.api.RoomCreation.*;
import io.srmppn.HappyDorm.room.api.RoomReservation.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class RoomAggregate {
    @AggregateIdentifier
    public UUID roomId;

    public boolean isReserved;

    /**
     * default constructor for axon
     */
    public RoomAggregate() {
    }

    @CommandHandler
    public RoomAggregate(CreateRoomCommand command) {
        AggregateLifecycle.apply(new RoomCreatedEvent(command.getRoomId()));
    }

    @EventSourcingHandler
    public void on(RoomCreatedEvent event) {
        roomId = event.getRoomId();
    }

    @CommandHandler
    public void handle(ReserveRoomCommand command) {
        if (isReserved) {
            throw new RoomAlreadyReservedException();
        }
        AggregateLifecycle.apply(new RoomReservedEvent(command.getRoomId(),
                                                       command.getAccountId()));
    }

    @EventSourcingHandler
    public void on(RoomReservedEvent event) {
        isReserved = true;
    }

    @CommandHandler
    public void handle(UnreserveRoomCommand command) {
        AggregateLifecycle.apply(new RoomUnreservedEvent(command.getRoomId()));
    }

    @EventSourcingHandler
    public void on(RoomUnreservedEvent event) {
        isReserved = false;
    }
}
