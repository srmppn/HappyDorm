package io.srmppn.HappyDorm.billing.api;

import java.util.UUID;

public class BillPayment {
    public static class BillPaidEvent {

        public UUID billId;
        public UUID roomId;

        public BillPaidEvent(UUID billId, UUID roomId) {
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

    public static class BillExpiredEvent {

        public UUID billId;
        public UUID roomId;

        public BillExpiredEvent(UUID billId, UUID roomId) {
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
