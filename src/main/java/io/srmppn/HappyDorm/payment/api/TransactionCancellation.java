package io.srmppn.HappyDorm.payment.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class TransactionCancellation {
    public static class CancelTransactionCommand {
        @TargetAggregateIdentifier
        public String transactionId;

        public CancelTransactionCommand(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getTransactionId() {
            return transactionId;
        }
    }

    public static class TransactionCancelledEvent {
        @TargetAggregateIdentifier
        public String transactionId;

        public TransactionCancelledEvent(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getTransactionId() {
            return transactionId;
        }
    }
}
