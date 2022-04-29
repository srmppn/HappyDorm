package io.srmppn.happydorm.room.api

import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.serialization.Revision
import java.util.*

class CreateRoomCommand(
  @TargetAggregateIdentifier
  val roomId: UUID
)

@Revision("1.0")
class RoomCreatedEvent(
  val roomId: UUID
)