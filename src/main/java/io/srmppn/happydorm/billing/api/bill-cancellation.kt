package io.srmppn.happydorm.billing.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.serialization.Revision
import java.util.*

data class CancelBillCommand(
  @TargetAggregateIdentifier
  val billId: UUID
)

@Revision("1.0")
data class BillCancelledEvent(
  @TargetAggregateIdentifier
  val billId: UUID
)