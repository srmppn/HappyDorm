package io.srmppn.HappyDorm.payment.service;

public class InvalidPaymentException extends IllegalArgumentException {
    public final static String DEFAULT_EXCEPTION_MSG = "Invalid payment parameters";

    public InvalidPaymentException() {
        super(DEFAULT_EXCEPTION_MSG);
    }
}
