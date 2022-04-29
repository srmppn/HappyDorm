package io.srmppn.happydorm.room.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.serialization.Revision
import java.util.*

data class ReserveRoomCommand(
  @TargetAggregateIdentifier
  val roomId: UUID,
  val accountId: UUID
)

@Revision("1.0")
data class RoomReservedEvent(
  val roomId: UUID,
  val accountId: UUID
)

class UnreserveRoomCommand(
  @TargetAggregateIdentifier
  val roomId: UUID
)

@Revision("1.0")
class RoomUnreservedEvent(
  val roomId: UUID
)

@Revision("1.0")
class RoomReservationExpiredEvent(
  val roomId: UUID
)

@Revision("1.0")
data class RoomRentedEvent(
  val roomId: UUID
)