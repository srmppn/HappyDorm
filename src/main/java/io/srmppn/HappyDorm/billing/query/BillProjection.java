package io.srmppn.HappyDorm.billing.query;

import io.srmppn.HappyDorm.billing.api.BillPayment.*;
import io.srmppn.HappyDorm.billing.api.BillingCreation.BillCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillProjection {

    @Autowired
    private BillRepository billRepository;

    @EventHandler
    public void on(BillCreatedEvent event) {
        billRepository.save(new Bill(event.getBillId(),
                                     event.getRoomId(),
                                     BillStatus.Pending))
                      .block();
    }

    @EventHandler
    public void on(BillExpiredEvent event) {
        billRepository.findById(event.getBillId())
                      .map(Bill::expired)
                      .block();
    }

    @EventHandler
    public void on(BillPaidEvent event) {
        billRepository.findById(event.getBillId())
                      .map(Bill::paid)
                      .block();
    }
}
