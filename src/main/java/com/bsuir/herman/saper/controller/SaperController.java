package com.bsuir.herman.saper.controller;

import com.bsuir.herman.auth.Debug;
import com.bsuir.herman.auth.service.UserService;
import com.bsuir.herman.saper.entity.DTO.RoomDTO;
import com.bsuir.herman.saper.entity.DTO.GameDto;
import com.bsuir.herman.saper.entity.Room;
import com.bsuir.herman.ws.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/saper/")
public class SaperController {

//    @GetMapping("token")
//    public AuthenticationRequestDto tokenModel() {
//        AuthenticationRequestDto dto = new AuthenticationRequestDto();
//        dto.setUsername("Username");
//        dto.setPassword("Password");
//        return dto;
//    }

    @GetMapping("test")
    public ResponseEntity<String> greeting() {
        Debug.printMapping("/api/v1/saper/test");
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    private final UserService userService;

    @Autowired
    public SaperController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("room/create")
    public ResponseEntity<Room> createRoom(
            @RequestBody GameDto gameDto
            ) {
        Debug.printMapping("/api/v1/saper/room/create");
        Debug.printInfo("Created game room: "+ gameDto.toString());
        return new ResponseEntity<>(new GameManager().createRoom(gameDto), HttpStatus.OK);
    }

    @GetMapping("rooms")
    public ResponseEntity<List<RoomDTO>> selectAll() {
        Debug.printMapping("/api/v1/saper/rooms");
        List<Room> rooms = new GameManager().getAllRooms();
        List<RoomDTO> roomDTOList = new RoomDTO().convertList(rooms);
        Debug.printInfo("In this moment exist(s) "+rooms.size());
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }

}
