package io.srmppn.HappyDorm.room.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RoomCreation {

    public static class CreateRoomCommand {
        @TargetAggregateIdentifier
        private String roomId;

        public CreateRoomCommand(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }
    }

    public static class RoomCreatedEvent {
        private String roomId;

        public RoomCreatedEvent(String roomId) {
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
