package io.srmppn.HappyDorm.dorm.api;

import io.srmppn.HappyDorm.dorm.command.DormPolicy;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class DormCreation {
    public static class CreateDormCommand {
        @TargetAggregateIdentifier
        public String dormId;
        public String dormName;
        public DormPolicy dormPolicy;

        public CreateDormCommand(String dormId, String dormName, DormPolicy dormPolicy) {
            this.dormId = dormId;
            this.dormName = dormName;
            this.dormPolicy = dormPolicy;
        }

        public String getDormId() {
            return dormId;
        }

        public String getDormName() {
            return dormName;
        }


        public DormPolicy getDormPolicy() {
            return dormPolicy;
        }
    }

    public static class DormCreatedEvent {
        public String dormId;
        public String dormName;
        public DormPolicy dormPolicy;

        public DormCreatedEvent(String dormId, String dormName, DormPolicy dormPolicy) {
            this.dormId = dormId;
            this.dormName = dormName;
            this.dormPolicy = dormPolicy;
        }

        public String getDormId() {
            return dormId;
        }

        public String getDormName() {
            return dormName;
        }

        public DormPolicy getDormPolicy() {
            return dormPolicy;
        }
    }
}
