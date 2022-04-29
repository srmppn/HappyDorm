package io.srmppn.happydorm.room.query;

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
                      .map(Room::unreserve)
                      .flatMap(roomRepository::save)
                      .block();
    }

    @EventHandler
    public void on(RoomRentedEvent event) {
        roomRepository.findById(event.getRoomId())
                      .map(Room::rent)
                      .flatMap(roomRepository::save)
                      .block();
    }
}
