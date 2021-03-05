package com.bsuir.herman.auth.controller;


import com.bsuir.herman.auth.Debug;
import com.bsuir.herman.auth.dto.UserDto;
import com.bsuir.herman.auth.model.User;
import com.bsuir.herman.auth.service.UserService;
import com.bsuir.herman.saper.DTOMessage;
import com.bsuir.herman.saper.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bsuir.herman.saper.RoomStorage.roomArrayList;


@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity createRoom() {
        Debug.printMapping("/api/v1/users/create");
        roomArrayList.add(new Room());

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("write")
    public ResponseEntity writeToRoom(
            @RequestBody DTOMessage message
    ) {
        Room room = roomArrayList.get(message.id-1);
        room.setLog(room.getLog() + "\n"+message.message);
        System.out.println("-----------\n"+ roomArrayList.get(message.id-1).getLog());

        return new ResponseEntity(HttpStatus.OK);
    }
}
