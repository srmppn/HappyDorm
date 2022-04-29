package io.srmppn.happydorm.account.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.serialization.Revision
import java.util.*

data class AssignRoomCommand(
  @TargetAggregateIdentifier
  val accountId: UUID,
  val roomId: UUID
)

@Revision("1.0")
data class RoomAssignedEvent(
  val accountId: UUID,
  val roomId: UUID
)

data class UnAssignRoomCommand(
  @TargetAggregateIdentifier
  val accountId: UUID
)

@Revision("1.0")
data class RoomUnAssignedEvent(
  var accountId: UUID
)