package io.srmppn.happydorm.billing.service.dummy;

import io.srmppn.happydorm.billing.service.InvalidPaymentException;
import io.srmppn.happydorm.billing.command.Payment;
import io.srmppn.happydorm.billing.service.PaymentGateway;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DummyPaymentGateway implements PaymentGateway<Payment> {
    @Override
    public Payment createPayment(BigDecimal paymentAmount) throws InvalidPaymentException {
        if (paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPaymentException();
        }

        return new Payment(UUID.randomUUID(), generateSecret());
    }

    @Override
    public void cancelPayment(Payment payment) throws InvalidPaymentException {
        // do cancel
        return;
    }
    private String generateSecret() {
        return ObjectId.get().toHexString();
    }
}
