package io.srmppn.HappyDorm.payment.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class TransactionVerification {
    public static class VerifyTransactionCommand {

        @TargetAggregateIdentifier
        public String transactionId;
        public String paymentSecret;

        public VerifyTransactionCommand(String transactionId, String paymentSecret) {
            this.transactionId = transactionId;
            this.paymentSecret = paymentSecret;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public String getPaymentSecret() {
            return paymentSecret;
        }
    }

    public static class TransactionVerifiedEvent {

        public String transactionId;
        public String billId;

        public TransactionVerifiedEvent(String transactionId, String billId) {
            this.transactionId = transactionId;
            this.billId = billId;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public String getBillId() {
            return billId;
        }
    }
}
