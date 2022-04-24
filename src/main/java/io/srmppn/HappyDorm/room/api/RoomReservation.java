package io.srmppn.HappyDorm.room.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class RoomReservation {
    public static class ReserveRoomCommand {
        @TargetAggregateIdentifier
        private UUID roomId;
        private UUID accountId;

        public ReserveRoomCommand(UUID roomId, UUID accountId) {
            this.roomId = roomId;
            this.accountId = accountId;
        }

        public UUID getRoomId() {
            return roomId;
        }

        public UUID getAccountId() {
            return accountId;
        }
    }

    public static class RoomReservedEvent {
        private UUID roomId;
        private UUID accountId;

        public RoomReservedEvent(UUID roomId, UUID accountId) {
            this.roomId = roomId;
            this.accountId = accountId;
        }

        public UUID getRoomId() {
            return roomId;
        }

        public void setRoomId(UUID roomId) {
            this.roomId = roomId;
        }

        public UUID getAccountId() {
            return accountId;
        }
    }

    public static class UnreserveRoomCommand {
        @TargetAggregateIdentifier
        private UUID roomId;

        public UnreserveRoomCommand(UUID roomId) {
            this.roomId = roomId;
        }

        public UUID getRoomId() {
            return roomId;
        }
    }

    public static class RoomUnreservedEvent {
        private UUID roomId;

        public RoomUnreservedEvent(UUID roomId) {
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
