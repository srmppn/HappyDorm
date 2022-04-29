package io.srmppn.happydorm.billing.command;

public class PaymentAlreadyVerifiedException extends IllegalArgumentException {
    public final static String DEFAULT_EXCEPTION_MSG = "Payment has been already verified.";

    public PaymentAlreadyVerifiedException() {
        super(DEFAULT_EXCEPTION_MSG);
    }
}
