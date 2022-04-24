package io.srmppn.HappyDorm.payment.command;

import io.srmppn.HappyDorm.payment.api.TransactionCancellation.*;
import io.srmppn.HappyDorm.payment.api.TransactionCreation.*;
import io.srmppn.HappyDorm.payment.api.TransactionVerification.*;
import io.srmppn.HappyDorm.payment.service.dummy.DummyPaymentGateway;
import io.srmppn.HappyDorm.payment.service.dummy.Payment;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class TransactionAggregate {
    @AggregateIdentifier
    private UUID transactionId;
    private UUID billId;
    private Payment payment;
    private boolean isPaymentVerified = false;

    /**
     * default constructor for axon
     */
    public TransactionAggregate() {
    }

    @CommandHandler
    public TransactionAggregate(CreateTransactionCommand command, DummyPaymentGateway paymentGateway) {
        Payment payment = paymentGateway.createPayment(command.getPaymentAmount());
        AggregateLifecycle.apply(new TransactionCreatedEvent(command.getTransactionId(),
                                                             command.getBillId(),
                                                             payment));
    }

    @EventSourcingHandler
    public void on(TransactionCreatedEvent event) {
        transactionId = event.getTransactionId();
        billId = event.getBillId();
        payment = event.getPayment();
    }

    @CommandHandler
    public void handle(VerifyTransactionCommand command) {
        boolean isValidSecret = verifySecret(command.getPaymentSecret());
        if (!isValidSecret) {
            throw new InvalidPaymentSecretException();
        }
        if (isPaymentVerified) {
            throw new PaymentAlreadyVerifiedException();
        }
        AggregateLifecycle.apply(new TransactionVerifiedEvent(command.getTransactionId(), billId));
    }

    @EventSourcingHandler
    public void on(TransactionVerifiedEvent event) {
        isPaymentVerified = true;
    }

    @CommandHandler
    public void handle(CancelTransactionCommand command, DummyPaymentGateway paymentGateway) {
        if (isPaymentVerified) {
            throw new PaymentAlreadyVerifiedException();
        }
        paymentGateway.cancelPayment(payment);
        AggregateLifecycle.apply(new TransactionCancelledEvent(command.getTransactionId()));
    }

    public boolean verifySecret(String secret) {
        return payment.getPaymentSecret().equals(secret);
    }
}
