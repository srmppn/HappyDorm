package io.srmppn.happydorm.billing.command;

import io.srmppn.happydorm.billing.api.CreateBillCommand;
import io.srmppn.happydorm.billing.service.dummy.DummyPaymentGateway;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class MonthlyBillAggregate extends BillAggregate<MonthlySummary> {

    @CommandHandler
    public MonthlyBillAggregate(CreateBillCommand<MonthlySummary> command, DummyPaymentGateway paymentGateway) {
        super(command, paymentGateway);
    }
}
