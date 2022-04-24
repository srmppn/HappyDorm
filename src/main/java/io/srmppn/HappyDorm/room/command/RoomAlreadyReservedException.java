package io.srmppn.HappyDorm.room.command;

public class RoomAlreadyReservedException extends IllegalArgumentException {
    public final static String DEFAULT_EXCEPTION_MSG = "Room is already reserved.";

    public RoomAlreadyReservedException() {
        super(DEFAULT_EXCEPTION_MSG);
    }
}
