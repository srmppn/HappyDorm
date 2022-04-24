package io.srmppn.HappyDorm.payment.api;

import io.srmppn.HappyDorm.payment.service.dummy.Payment;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionCreation {

    public static class CreateTransactionCommand {

        @TargetAggregateIdentifier
        public UUID transactionId;
        public UUID billId;
        public BigDecimal paymentAmount;

        public CreateTransactionCommand(UUID transactionId, UUID billId, BigDecimal paymentAmount) {
            this.transactionId = transactionId;
            this.billId = billId;
            this.paymentAmount = paymentAmount;
        }

        public UUID getTransactionId() {
            return transactionId;
        }

        public BigDecimal getPaymentAmount() {
            return paymentAmount;
        }

        public UUID getBillId() {
            return billId;
        }
    }

    public static class TransactionCreatedEvent {

        public UUID transactionId;
        public UUID billId;
        public Payment payment;

        public TransactionCreatedEvent(UUID transactionId, UUID billId, Payment payment) {
            this.transactionId = transactionId;
            this.billId = billId;
            this.payment = payment;
        }


        public UUID getTransactionId() {
            return transactionId;
        }

        public UUID getBillId() {
            return billId;
        }

        public Payment getPayment() {
            return payment;
        }
    }
}
