package io.srmppn.HappyDorm.room.query;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends ReactiveCrudRepository<Room, UUID> {
}
