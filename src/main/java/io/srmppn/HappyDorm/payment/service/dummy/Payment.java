package io.srmppn.HappyDorm.payment.service.dummy;

import java.util.Objects;

public class Payment {

    private String paymentReferenceId;
    private String paymentSecret;

    public Payment(String paymentReferenceId, String paymentSecret) {
        Objects.requireNonNull(paymentReferenceId);
        Objects.requireNonNull(paymentSecret);
        this.paymentReferenceId = paymentReferenceId;
        this.paymentSecret = paymentSecret;
    }

    public String getPaymentReferenceId() {
        return paymentReferenceId;
    }

    public String getPaymentSecret() {
        return paymentSecret;
    }
}
