package io.srmppn.HappyDorm.billing.command;

import io.srmppn.HappyDorm.billing.api.BillingCreation.CreateBillCommand;
import io.srmppn.HappyDorm.billing.service.dummy.DummyPaymentGateway;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class MonthlyBillAggregate extends BillAggregate<MonthlySummary> {

    @CommandHandler
    public MonthlyBillAggregate(CreateBillCommand<MonthlySummary> command, DummyPaymentGateway paymentGateway) {
        super(command, paymentGateway);
    }
}
