package io.srmppn.HappyDorm.payment.service.dummy;

import io.srmppn.HappyDorm.payment.service.InvalidPaymentException;
import io.srmppn.HappyDorm.payment.service.PaymentGateway;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class DummyPaymentGateway implements PaymentGateway<Payment, BigDecimal> {
    @Override
    public Payment createPayment(BigDecimal paymentAmount) throws InvalidPaymentException {
        if (paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPaymentException();
        }
        return new Payment(UUID.randomUUID().toString(), ObjectId.get().toHexString());
    }

    @Override
    public void cancelPayment(Payment payment) throws InvalidPaymentException {
        // do cancel
        return;
    }
}
