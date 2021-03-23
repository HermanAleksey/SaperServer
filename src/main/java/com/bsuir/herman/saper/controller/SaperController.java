package com.bsuir.herman.saper.controller;

import com.bsuir.herman.auth.Debug;
import com.bsuir.herman.auth.dto.AuthenticationRequestDto;
import com.bsuir.herman.auth.dto.MessageDto;
import com.bsuir.herman.auth.dto.RegistrationRequestDto;
import com.bsuir.herman.auth.model.User;
import com.bsuir.herman.auth.service.UserService;
import com.bsuir.herman.saper.PlayerDTO;
import com.bsuir.herman.saper.RoomDTO;
import com.bsuir.herman.saper.entity.Room;
import com.bsuir.herman.ws.GameManager;
import org.apache.catalina.connector.Response;
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

    @GetMapping("room/create")
    public ResponseEntity<Room> createRoom() {
        Debug.printMapping("/api/v1/saper/room/create");
        return new ResponseEntity<>(new GameManager().createRoom(), HttpStatus.OK);
    }

    @GetMapping("rooms")
    public ResponseEntity<List<RoomDTO>> selectAll() {
        Debug.printMapping("/api/v1/saper/rooms");
        List<Room> rooms = new GameManager().getAllRooms();
        List<RoomDTO> roomDTOList = new RoomDTO().convertList(rooms);
        Debug.printInfo("In this moment exist(s) "+rooms.size());
        return new ResponseEntity<>(roomDTOList, HttpStatus.OK);
    }

    @GetMapping("room/join")
    public ResponseEntity joinRoom(@RequestBody PlayerDTO playerDTO) {
        Debug.printMapping("/api/v1/saper/room/join");

        playerDTO.getId();
//        List<Room> rooms = new GameManager().joinRoom();


        return new ResponseEntity<>(HttpStatus.OK);
    }

}
