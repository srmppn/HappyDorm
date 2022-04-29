package io.srmppn.happydorm.billing.command;

import java.math.BigDecimal;

public interface BillingSummary {
    BigDecimal totalCost();
}
