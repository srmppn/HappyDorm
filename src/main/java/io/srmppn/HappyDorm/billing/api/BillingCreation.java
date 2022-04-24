package io.srmppn.HappyDorm.billing.api;

import io.srmppn.HappyDorm.billing.command.BillingSummary;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Duration;
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

        public Duration expiry;

        public BillCreatedEvent(UUID billId, UUID roomId, T summary, Duration expiry) {
            this.billId = billId;
            this.roomId = roomId;
            this.summary = summary;
            this.expiry = expiry;
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

        public Duration getExpiry() {
            return expiry;
        }
    }
}
