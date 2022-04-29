package io.srmppn.happydorm.billing.command;

public class InvalidPaymentSecretException extends IllegalArgumentException {
    public final static String DEFAULT_EXCEPTION_MSG = "Payment has been already verified.";

    public InvalidPaymentSecretException() {
        super(DEFAULT_EXCEPTION_MSG);
    }
}
