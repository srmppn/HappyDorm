package io.srmppn.HappyDorm.dorm.command;

import io.srmppn.HappyDorm.dorm.api.DormCreation.*;
import io.srmppn.HappyDorm.room.command.RoomAggregate;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class DormAggregate {

    @AggregateIdentifier
    private String dormId;
    private DormPolicy dormPolicy;

    /**
     * default constructor for axon
     */
    public DormAggregate() {

    }

    @CommandHandler
    public DormAggregate(CreateDormCommand command) {
        AggregateLifecycle.apply(new DormCreatedEvent(command.getDormId(),
                                                      command.getDormName(),
                                                      command.getDormPolicy()));
    }

    @EventSourcingHandler
    public void handle(DormCreatedEvent event) {
        dormId = event.getDormId();
        dormPolicy = event.getDormPolicy();
    }
}
