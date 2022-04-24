package io.srmppn.HappyDorm.billing.query;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Bill {
    @Id
    public UUID billId;
    public UUID roomId;

    public BillStatus status;

    public Bill(UUID billId, UUID roomId, BillStatus status) {
        this.billId = billId;
        this.roomId = roomId;
        this.status = status;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public Bill verify() {
        setStatus(BillStatus.Verified);
        return this;
    }
}
