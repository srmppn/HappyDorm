package io.srmppn.HappyDorm.account.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

/* should this put on the room aggregate instead ? hmmmm...
 */
public class RoomAssign {
    public static class AssignRoomCommand {
        @TargetAggregateIdentifier
        public UUID accountId;
        public UUID roomId;

        public AssignRoomCommand(UUID accountId, UUID roomId) {
            this.accountId = accountId;
            this.roomId = roomId;
        }

        public UUID getAccountId() {
            return accountId;
        }

        public UUID getRoomId() {
            return roomId;
        }
    }

    public static class RoomAssignedEvent {
        public UUID accountId;
        public UUID roomId;

        public RoomAssignedEvent(UUID accountId, UUID roomId) {
            this.accountId = accountId;
            this.roomId = roomId;
        }

        public UUID getAccountId() {
            return accountId;
        }

        public UUID getRoomId() {
            return roomId;
        }
    }

    public static class UnAssignRoomCommand {
        @TargetAggregateIdentifier
        public UUID accountId;

        public UnAssignRoomCommand(UUID accountId) {
            this.accountId = accountId;
        }

        public UUID getAccountId() {
            return accountId;
        }
    }

    public static class RoomUnAssignedEvent {
        public UUID accountId;

        public RoomUnAssignedEvent(UUID accountId) {
            this.accountId = accountId;
        }

        public UUID getAccountId() {
            return accountId;
        }
    }
}
