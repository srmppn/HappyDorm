package io.srmppn.HappyDorm.account.api;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.UUID;

public class AccountCreation {
    // do change and move to env.
    public final static String SECRET_SALT = "salt";

    public static class CreateAccountCommand {

        @TargetAggregateIdentifier
        public UUID accountId;
        public String firstName;
        public String lastName;
        public String username;
        public String password;

        public CreateAccountCommand(UUID accountId,
                                    String firstName,
                                    String lastName,
                                    String username,
                                    String password) {
            this.accountId = accountId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
        }

        public UUID getAccountId() {
            return accountId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getUsername() {
            return username;
        }

        public String getHashedPassword() {
            return BCrypt.hashpw(password, SECRET_SALT);
        }
    }

    public static class AccountCreatedEvent {

        public UUID accountId;
        public String firstName;
        public String lastName;
        public String username;
        public String hashedPassword;

        public AccountCreatedEvent(UUID accountId,
                                   String firstName,
                                   String lastName,
                                   String username,
                                   String hashedPassword) {
            this.accountId = accountId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.hashedPassword = hashedPassword;
        }

        public UUID getAccountId() {
            return accountId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getUsername() {
            return username;
        }

        public String getHashedPassword() {
            return hashedPassword;
        }
    }
}
