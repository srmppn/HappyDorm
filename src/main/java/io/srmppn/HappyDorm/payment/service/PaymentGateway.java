package io.srmppn.HappyDorm.payment.service;

/**
 * interface describing payment gateway
 *
 * @param <T> generic type defining the type of payment
 * @param <C> generic type defining the currency of payment
 */
public interface PaymentGateway<T, C> {

    T createPayment(C paymentAmount) throws InvalidPaymentException;

    void cancelPayment(T payment) throws InvalidPaymentException;
}
