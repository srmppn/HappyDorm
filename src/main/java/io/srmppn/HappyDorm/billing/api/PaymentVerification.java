package io.srmppn.HappyDorm.billing.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class PaymentVerification {
    public static class VerifyPaymentCommand {
        @TargetAggregateIdentifier
        public UUID billId;
        public String paymentSecret;

        public VerifyPaymentCommand(UUID billId, String paymentSecret) {
            this.billId = billId;
            this.paymentSecret = paymentSecret;
        }

        public UUID getBillId() {
            return billId;
        }

        public String getPaymentSecret() {
            return paymentSecret;
        }
    }

    public static class PaymentVerifiedEvent {
        public UUID billId;
        public UUID roomId;

        public PaymentVerifiedEvent(UUID billId, UUID roomId) {
            this.billId = billId;
            this.roomId = roomId;
        }

        public UUID getBillId() {
            return billId;
        }

        public void setBillId(UUID billId) {
            this.billId = billId;
        }

        public UUID getRoomId() {
            return roomId;
        }

        public void setRoomId(UUID roomId) {
            this.roomId = roomId;
        }
    }
}
