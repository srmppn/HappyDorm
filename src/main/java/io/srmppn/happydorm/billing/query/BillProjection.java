package io.srmppn.happydorm.billing.query;

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
    public void on(PaymentVerifiedEvent event) {
        billRepository.findById(event.getBillId())
                      .map(Bill::verify)
                      .block();
    }
}
