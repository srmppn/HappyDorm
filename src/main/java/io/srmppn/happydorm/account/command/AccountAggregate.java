package io.srmppn.happydorm.account.command;

import io.srmppn.happydorm.account.api.AccountCreatedEvent;
import io.srmppn.happydorm.account.api.AssignRoomCommand;
import io.srmppn.happydorm.account.api.CreateAccountCommand;
import io.srmppn.happydorm.account.api.RoomAssignedEvent;
import io.srmppn.happydorm.account.api.RoomUnAssignedEvent;
import io.srmppn.happydorm.account.api.UnAssignRoomCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private UUID accountId;
    private UUID roomId;

    /**
     * default constructor for axon
     */
    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        AggregateLifecycle.apply(new AccountCreatedEvent(command.getAccountId(),
                                                         command.getFirstName(),
                                                         command.getLastName(),
                                                         command.getUsername(),
                                                         command.getHashedPassword()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        accountId = event.getAccountId();
    }

    @CommandHandler
    public void handle(AssignRoomCommand command) {
        if (hasRoom()){
            throw new RoomAlreadyAssignedException();
        }
        AggregateLifecycle.apply(new RoomAssignedEvent(command.getAccountId(),
                                                       command.getRoomId()));
    }

    @EventSourcingHandler
    public void on(RoomAssignedEvent event) {
        roomId = event.getRoomId();
    }

    @CommandHandler
    public void handle(UnAssignRoomCommand command) {
        AggregateLifecycle.apply(new RoomUnAssignedEvent(command.getAccountId()));
    }

    @EventSourcingHandler
    public void on(RoomUnAssignedEvent event) {
        roomId = null;
    }

    public boolean hasRoom() {
        return roomId != null;
    }
}
