package io.srmppn.HappyDorm.billing.command;

import io.srmppn.HappyDorm.billing.api.BillingCreation.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;
import java.time.Duration;

@Aggregate
public class Billing {
    public final static double electricityRate = 0.7;
    public final static double waterRate = 0.3;

    @AggregateIdentifier
    private String billId;

    @CommandHandler
    public Billing(CreateBillCommand command) {
        BigDecimal totalCost = calculateCost(command.getElectricityUsage(), command.getWaterUsage());
        AggregateLifecycle.apply(new BillCreatedEvent(command.getBillId(),
                                                      command.getRoomId(),
                                                      totalCost,
                                                      hasDuration()));
    }

    @EventSourcingHandler
    public void on(BillCreatedEvent event) {
        billId = event.getBillId();
    }

    public BigDecimal calculateCost(Double electricityUsage, Double waterUsage) {
        double electricityCost = electricityUsage * electricityRate;
        double waterCost = waterUsage * waterRate;
        return new BigDecimal(electricityCost + waterCost);
    }

    public Duration hasDuration() {
        return Duration.ofDays(14);
    }
}
