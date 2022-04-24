package io.srmppn.HappyDorm.account.command;

public class RoomAlreadyAssignException extends IllegalArgumentException {
    public final static String DEFAULT_EXCEPTION_MSG = "This account already has room.";

    public RoomAlreadyAssignException() {
        super(DEFAULT_EXCEPTION_MSG);
    }
}
