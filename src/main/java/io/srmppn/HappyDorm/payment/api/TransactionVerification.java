package io.srmppn.HappyDorm.payment.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class TransactionVerification {
    public static class VerifyTransactionCommand {

        @TargetAggregateIdentifier
        public UUID transactionId;
        public String paymentSecret;

        public VerifyTransactionCommand(UUID transactionId, String paymentSecret) {
            this.transactionId = transactionId;
            this.paymentSecret = paymentSecret;
        }

        public UUID getTransactionId() {
            return transactionId;
        }

        public String getPaymentSecret() {
            return paymentSecret;
        }
    }

    public static class TransactionVerifiedEvent {

        public UUID transactionId;
        public UUID billId;

        public TransactionVerifiedEvent(UUID transactionId, UUID billId) {
            this.transactionId = transactionId;
            this.billId = billId;
        }

        public UUID getTransactionId() {
            return transactionId;
        }

        public UUID getBillId() {
            return billId;
        }
    }
}
