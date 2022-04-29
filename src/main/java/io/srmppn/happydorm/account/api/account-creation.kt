package io.srmppn.happydorm.account.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.serialization.Revision
import org.springframework.security.crypto.bcrypt.BCrypt
import java.util.*

data class CreateAccountCommand(
  @TargetAggregateIdentifier
  val accountId: UUID,
  val firstName: String,
  val lastName: String,
  val username: String,
  val password: String
) {
  fun getHashedPassword(): String {
    return BCrypt.hashpw(password, "salt")
  }
}

@Revision("1.0")
data class AccountCreatedEvent(
  val accountId: UUID,
  val firstName: String,
  val lastName: String,
  val username: String,
  val hashedPassword: String
)