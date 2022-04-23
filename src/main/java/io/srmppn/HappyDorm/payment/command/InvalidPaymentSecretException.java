package io.srmppn.HappyDorm.payment.command;

public class InvalidPaymentSecretException extends IllegalArgumentException {
    public final static String DEFAULT_EXCEPTION_MSG = "Invalid secret.";

    public InvalidPaymentSecretException() {
        super(DEFAULT_EXCEPTION_MSG);
    }
}
