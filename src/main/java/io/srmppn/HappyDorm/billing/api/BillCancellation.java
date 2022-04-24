package io.srmppn.HappyDorm.billing.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class BillCancellation {
    public static class CancelBillCommand {
        @TargetAggregateIdentifier
        public UUID billId;

        public CancelBillCommand(UUID billId) {
            this.billId = billId;
        }

        public UUID getBillId() {
            return billId;
        }

        public void setBillId(UUID billId) {
            this.billId = billId;
        }
    }

    public static class BillCancelledEvent {
        @TargetAggregateIdentifier
        public UUID billId;

        public BillCancelledEvent(UUID billId) {
            this.billId = billId;
        }

        public UUID getBillId() {
            return billId;
        }

        public void setBillId(UUID billId) {
            this.billId = billId;
        }
    }
}
