package io.srmppn.HappyDorm.billing.command;

import io.srmppn.HappyDorm.billing.api.BillingCreation.*;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Duration;

@Aggregate
public class ReservationBillAggregate extends BillAggregate<ReservationSummary> {

    private Duration reservationExpiry;

    public ReservationBillAggregate(CreateBillCommand<ReservationSummary> command) {
        super(command);
    }

    @Override
    public Duration getExpiryDuration() {
        return reservationExpiry;
    }
}
