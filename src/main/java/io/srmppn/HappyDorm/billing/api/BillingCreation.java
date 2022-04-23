package io.srmppn.HappyDorm.billing.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.time.Duration;

public class BillingCreation {

    public static class CreateBillCommand {

        @TargetAggregateIdentifier
        public String billId;
        public String roomId;

        public double electricityUsage;
        public double waterUsage;

        public CreateBillCommand(String billId, String roomId, double electricityUsage, double waterUsage) {
            this.billId = billId;
            this.roomId = roomId;
            this.electricityUsage = electricityUsage;
            this.waterUsage = waterUsage;
        }

        public String getBillId() {
            return billId;
        }

        public String getRoomId() {
            return roomId;
        }

        public double getElectricityUsage() {
            return electricityUsage;
        }

        public double getWaterUsage() {
            return waterUsage;
        }
    }

    public static class BillCreatedEvent {

        public String billId;
        public String roomId;
        public BigDecimal paymentAmount;
        public Duration expiry;

        public BillCreatedEvent(String billId, String roomId, BigDecimal paymentAmount, Duration expiry) {
            this.billId = billId;
            this.roomId = roomId;
            this.paymentAmount = paymentAmount;
            this.expiry = expiry;
        }

        public String getBillId() {
            return billId;
        }

        public void setBillId(String billId) {
            this.billId = billId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public Duration getExpiry() {
            return expiry;
        }

        public void setExpiry(Duration expiry) {
            this.expiry = expiry;
        }

        public BigDecimal getPaymentAmount() {
            return paymentAmount;
        }

        public void setPaymentAmount(BigDecimal paymentAmount) {
            this.paymentAmount = paymentAmount;
        }
    }

    public static class BillExpiredEvent {

        public String billId;
        public String roomId;

        public BillExpiredEvent(String billId, String roomId) {
            this.billId = billId;
            this.roomId = roomId;
        }

        public String getBillId() {
            return billId;
        }

        public void setBillId(String billId) {
            this.billId = billId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }
    }
}
