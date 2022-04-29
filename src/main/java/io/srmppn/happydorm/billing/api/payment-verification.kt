package io.srmppn.happydorm.billing.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.serialization.Revision
import java.util.*


data class VerifyPaymentCommand(
  @TargetAggregateIdentifier
  val billId: UUID,
  val paymentSecret: String
)

@Revision("1.0")
data class PaymentVerifiedEvent(
  val billId: UUID,
  val roomId: UUID
)