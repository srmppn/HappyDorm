package io.srmppn.HappyDorm.room.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RoomReservation {
    public static class ReserveRoomCommand {
        @TargetAggregateIdentifier
        private String roomId;

        public ReserveRoomCommand(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }
    }

    public static class RoomReservedEvent {
        private String roomId;

        public RoomReservedEvent(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }
    }
}
