package io.srmppn.HappyDorm.account.command;

import io.srmppn.HappyDorm.account.api.RoomAssign.*;
import io.srmppn.HappyDorm.account.api.AccountCreation.AccountCreatedEvent;
import io.srmppn.HappyDorm.account.api.AccountCreation.CreateAccountCommand;
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
            throw new RoomAlreadyAssignException();
        }
        AggregateLifecycle.apply(new RoomAssignedEvent(command.getAccountId(),
                                                       command.getRoomId()));
    }

    @EventSourcingHandler
    public void on(RoomAssignedEvent event) {
        roomId = event.getRoomId();
    }

    public boolean hasRoom() {
        return roomId != null;
    }
}
