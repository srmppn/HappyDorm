package io.srmppn.HappyDorm.billing.command;

import io.srmppn.HappyDorm.billing.api.PaymentVerification.PaymentVerifiedEvent;
import io.srmppn.HappyDorm.billing.api.PaymentVerification.VerifyPaymentCommand;
import io.srmppn.HappyDorm.billing.api.BillingCreation.BillCreatedEvent;
import io.srmppn.HappyDorm.billing.api.BillingCreation.CreateBillCommand;
import io.srmppn.HappyDorm.billing.service.PaymentGateway;
import io.srmppn.HappyDorm.billing.service.dummy.DummyPaymentGateway;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
abstract class BillAggregate<T extends BillingSummary> {

    @AggregateIdentifier
    public UUID billId;
    public UUID roomId;
    public T summary;

    @AggregateMember
    public Payment payment;

    @CommandHandler
    public BillAggregate(CreateBillCommand<T> command, DummyPaymentGateway paymentGateway) {
        Payment payment = paymentGateway.createPayment(command.getSummary().totalCost());
        AggregateLifecycle.apply(new BillCreatedEvent<>(command.getBillId(),
                                                        command.getRoomId(),
                                                        command.getSummary(),
                                                        payment));
    }

    @EventSourcingHandler
    public void on(BillCreatedEvent<T> event) {
        billId = event.getBillId();
        summary = event.getSummary();
        payment = event.getPayment();
        roomId = event.getRoomId();
    }

    @CommandHandler
    public void handle(VerifyPaymentCommand command) {
        if (payment.isVerified()) {
            throw new PaymentAlreadyVerifiedException();
        }
        if (payment.isValidSecret(command.getPaymentSecret())) {
            throw new InvalidPaymentSecretException();
        }
        AggregateLifecycle.apply(new PaymentVerifiedEvent(command.getBillId(), roomId));
    }

    @EventSourcingHandler
    public void on(PaymentVerifiedEvent event) {
        payment.setStatus(PaymentStatus.Verified);
    }
}
