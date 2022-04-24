package io.srmppn.HappyDorm.room.api;

import java.util.UUID;

public class RoomPaymentScheduling {
    public static class RoomRentedEvent {
        public UUID roomId;

        public RoomRentedEvent(UUID roomId) {
            this.roomId = roomId;
        }

        public UUID getRoomId() {
            return roomId;
        }

        public void setRoomId(UUID roomId) {
            this.roomId = roomId;
        }
    }
}
