package io.srmppn.HappyDorm.account.command;

public class RoomAlreadyAssignedException extends IllegalArgumentException {
    public final static String DEFAULT_EXCEPTION_MSG = "This account already has room.";

    public RoomAlreadyAssignedException() {
        super(DEFAULT_EXCEPTION_MSG);
    }
}
