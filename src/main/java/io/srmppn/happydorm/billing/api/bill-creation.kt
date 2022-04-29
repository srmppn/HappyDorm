package io.srmppn.happydorm.billing.api

import io.srmppn.happydorm.billing.command.BillingSummary
import io.srmppn.happydorm.billing.command.Payment
import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.serialization.Revision
import java.util.*

data class CreateBillCommand<T : BillingSummary>(
  @TargetAggregateIdentifier
  val billId: UUID,
  val roomId: UUID,
  val summary: T
)

@Revision("1.0")
data class BillCreatedEvent<T : BillingSummary>(
  val billId: UUID,
  val roomId: UUID,
  val summary: T,
  val payment: Payment
)