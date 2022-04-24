package io.srmppn.HappyDorm.billing.command;

import java.util.Objects;
import java.util.UUID;

public class Payment {

    private UUID paymentReferenceId;
    private String paymentSecret;
    private PaymentStatus status;

    public Payment(UUID paymentReferenceId, String paymentSecret) {
        Objects.requireNonNull(paymentReferenceId);
        Objects.requireNonNull(paymentSecret);
        this.paymentReferenceId = paymentReferenceId;
        this.paymentSecret = paymentSecret;
    }

    public UUID getPaymentReferenceId() {
        return paymentReferenceId;
    }

    public String getPaymentSecret() {
        return paymentSecret;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public boolean isVerified() {
        return status.equals(PaymentStatus.Verified);
    }

    public boolean isCancelled() {
        return status.equals(PaymentStatus.Cancel);
    }

    public boolean isValidSecret(String paymentSecret) {
        return this.paymentSecret.equals(paymentSecret);
    }
}
