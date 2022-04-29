package io.srmppn.happydorm.room.controller;


import io.srmppn.happydorm.room.api.CreateRoomCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private CommandGateway commandGateway;

}
