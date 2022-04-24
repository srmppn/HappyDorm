package io.srmppn.HappyDorm.billing.api;

import io.srmppn.HappyDorm.billing.command.BillingSummary;
import io.srmppn.HappyDorm.billing.command.Payment;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class BillingCreation {

    public static class CreateBillCommand<T extends BillingSummary> {

        @TargetAggregateIdentifier
        public UUID billId;
        public UUID roomId;

        public T summary;

        public CreateBillCommand(UUID billId, UUID roomId, T summary) {
            this.billId = billId;
            this.roomId = roomId;
            this.summary = summary;
        }

        public UUID getBillId() {
            return billId;
        }

        public UUID getRoomId() {
            return roomId;
        }

        public T getSummary() {
            return summary;
        }
    }

    public static class BillCreatedEvent<T extends BillingSummary> {

        public UUID billId;
        public UUID roomId;

        public T summary;
        public Payment payment;

        public BillCreatedEvent(UUID billId, UUID roomId, T summary, Payment payment) {
            this.billId = billId;
            this.roomId = roomId;
            this.summary = summary;
            this.payment = payment;
        }

        public UUID getBillId() {
            return billId;
        }

        public UUID getRoomId() {
            return roomId;
        }

        public T getSummary() {
            return summary;
        }

        public Payment getPayment() {
            return payment;
        }

        public void setPayment(Payment payment) {
            this.payment = payment;
        }
    }
}
