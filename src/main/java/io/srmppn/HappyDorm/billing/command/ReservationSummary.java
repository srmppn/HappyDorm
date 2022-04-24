package io.srmppn.HappyDorm.billing.command;

import java.math.BigDecimal;

public class ReservationSummary implements BillingSummary {
    public BigDecimal bindingCost;

    @Override
    public BigDecimal totalCost() {
        return bindingCost;
    }
}
