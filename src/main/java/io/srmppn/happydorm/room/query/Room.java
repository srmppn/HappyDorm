package io.srmppn.happydorm.room.query;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Room {
    @Id
    public UUID roomId;
    public RoomStatus status;
    public UUID accountId;

    public Room(UUID roomId, RoomStatus status) {
        this.roomId = roomId;
        this.status = status;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Room reserve(UUID accountId) {
        setAccountId(accountId);
        setStatus(RoomStatus.Reserved);
        return this;
    }

    public Room unreserve() {
        setStatus(RoomStatus.Available);
        return this;
    }

    public Room rent() {
        setStatus(RoomStatus.Rent);
        return this;
    }
}
