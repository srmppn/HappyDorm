package io.srmppn.HappyDorm.billing.command;

import io.srmppn.HappyDorm.billing.api.BillingCreation.BillCreatedEvent;
import io.srmppn.HappyDorm.billing.api.BillingCreation.CreateBillCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Duration;
import java.util.UUID;

@Aggregate
abstract class BillAggregate<T extends BillingSummary> {

    @AggregateIdentifier
    public UUID billId;
    public T summary;

    @CommandHandler
    public BillAggregate(CreateBillCommand<T> command) {
        AggregateLifecycle.apply(new BillCreatedEvent<>(command.getBillId(),
                                                        command.getRoomId(),
                                                        command.getSummary(),
                                                        getExpiryDuration()));
    }

    @EventSourcingHandler
    public void on(BillCreatedEvent<T> event) {
        billId = event.getBillId();
        summary = event.getSummary();
    }

    abstract public Duration getExpiryDuration();
}
