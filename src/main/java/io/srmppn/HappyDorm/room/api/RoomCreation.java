package io.srmppn.HappyDorm.room.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class RoomCreation {

    public static class CreateRoomCommand {
        @TargetAggregateIdentifier
        private UUID roomId;

        public CreateRoomCommand(UUID roomId) {
            this.roomId = roomId;
        }

        public UUID getRoomId() {
            return roomId;
        }

        public void setRoomId(UUID roomId) {
            this.roomId = roomId;
        }
    }

    public static class RoomCreatedEvent {
        private UUID roomId;

        public RoomCreatedEvent(UUID roomId) {
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
