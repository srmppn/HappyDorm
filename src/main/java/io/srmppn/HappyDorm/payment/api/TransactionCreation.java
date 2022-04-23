package io.srmppn.HappyDorm.payment.api;

import io.srmppn.HappyDorm.payment.service.dummy.Payment;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

public class TransactionCreation {

    public static class CreateTransactionCommand {

        @TargetAggregateIdentifier
        public String transactionId;
        public String billId;
        public BigDecimal paymentAmount;

        public CreateTransactionCommand(String transactionId, String billId, BigDecimal paymentAmount) {
            this.transactionId = transactionId;
            this.billId = billId;
            this.paymentAmount = paymentAmount;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public BigDecimal getPaymentAmount() {
            return paymentAmount;
        }

        public String getBillId() {
            return billId;
        }
    }

    public static class TransactionCreatedEvent {

        public String transactionId;
        public String billId;
        public Payment payment;

        public TransactionCreatedEvent(String transactionId, String billId, Payment payment) {
            this.transactionId = transactionId;
            this.billId = billId;
            this.payment = payment;
        }


        public String getTransactionId() {
            return transactionId;
        }

        public String getBillId() {
            return billId;
        }

        public Payment getPayment() {
            return payment;
        }
    }
}
