package io.srmppn.HappyDorm.billing.command;

import java.math.BigDecimal;

public class MonthlySummary implements BillingSummary {
    public BigDecimal electricityCost;
    public BigDecimal waterCost;
    public BigDecimal rentCost;

    public MonthlySummary(BigDecimal electricityCost, BigDecimal waterCost, BigDecimal rentCost) {
        this.electricityCost = electricityCost;
        this.waterCost = waterCost;
        this.rentCost = rentCost;
    }

    @Override
    public BigDecimal totalCost() {
        return electricityCost.add(waterCost).add(rentCost);
    }
}
