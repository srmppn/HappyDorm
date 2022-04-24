package io.srmppn.HappyDorm.billing.command;

import io.srmppn.HappyDorm.billing.api.BillCancellation.*;
import io.srmppn.HappyDorm.billing.api.BillingCreation.CreateBillCommand;
import io.srmppn.HappyDorm.billing.service.dummy.DummyPaymentGateway;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CommandHandlerInterceptor;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ReservationBillAggregate extends BillAggregate<ReservationSummary> {

    @CommandHandler
    public ReservationBillAggregate(CreateBillCommand<ReservationSummary> command, DummyPaymentGateway paymentGateway) {
        super(command, paymentGateway);
    }

    @CommandHandler
    public void handle(CancelBillCommand command, DummyPaymentGateway paymentGateway) {
        if (payment.isVerified()) {
            throw new PaymentAlreadyVerifiedException();
        }
        paymentGateway.cancelPayment(payment);
        AggregateLifecycle.apply(new BillCancelledEvent(command.getBillId()));
    }

    @EventSourcingHandler
    public void on(BillCancelledEvent event) {
        payment.setStatus(PaymentStatus.Cancel);
    }

    @CommandHandlerInterceptor
    public Object intercept(CommandMessage<?> command, InterceptorChain interceptorChain) throws Exception {
        if (payment.isCancelled()) {
            throw new BillAlreadyCancelledException();
        }

        return interceptorChain.proceed();
    }
}
