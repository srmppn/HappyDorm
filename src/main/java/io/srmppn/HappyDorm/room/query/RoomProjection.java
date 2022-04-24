package io.srmppn.HappyDorm.room.query;

import io.srmppn.HappyDorm.room.api.RoomCreation.*;
import io.srmppn.HappyDorm.room.api.RoomReservation.*;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;

@Component
public class RoomProjection {
    @Autowired
    @Transient
    private RoomRepository roomRepository;

    @EventHandler
    public void on(RoomCreatedEvent event) {
        roomRepository.save(new Room(event.getRoomId(),
                                     RoomStatus.Available))
                      .block();
    }

    @EventHandler
    public void on(RoomReservedEvent event) {
        roomRepository.findById(event.getRoomId())
                      .map(room -> room.reserve(event.getAccountId()))
                      .flatMap(roomRepository::save)
                      .block();
    }

    @EventHandler
    public void on(RoomUnreservedEvent event) {
        roomRepository.findById(event.getRoomId())
                      .map(room -> room.unreserve())
                      .flatMap(roomRepository::save)
                      .block();
    }
}