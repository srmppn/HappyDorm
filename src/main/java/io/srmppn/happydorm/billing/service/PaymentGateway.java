package io.srmppn.happydorm.billing.service;

import java.math.BigDecimal;

/**
 * interface describing payment gateway
 *
 * @param <T> generic type defining the type of payment
 * @param <C> generic type defining the currency of payment
 */
public interface PaymentGateway<T> {

    T createPayment(BigDecimal paymentAmount) throws InvalidPaymentException;

    void cancelPayment(T payment) throws InvalidPaymentException;
}
