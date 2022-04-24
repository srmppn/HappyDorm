package io.srmppn.HappyDorm.billing.command;

public class BillAlreadyCancelledException extends IllegalArgumentException {
    public final static String DEFAULT_EXCEPTION_MSG = "Payment has been already verified.";

    public BillAlreadyCancelledException() {
        super(DEFAULT_EXCEPTION_MSG);
    }
}
