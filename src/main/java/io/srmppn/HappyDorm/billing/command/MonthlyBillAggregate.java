package io.srmppn.HappyDorm.billing.command;

import io.srmppn.HappyDorm.billing.api.BillingCreation.CreateBillCommand;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Duration;

@Aggregate
public class MonthlyBillAggregate extends BillAggregate<MonthlySummary> {

    private final static Duration reGraceDays = Duration.ofDays(7);

    public MonthlyBillAggregate(CreateBillCommand<MonthlySummary> command) {
        super(command);
    }

    @Override
    public Duration getExpiryDuration() {
        return reGraceDays;
    }
}
