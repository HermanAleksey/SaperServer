package com.bsuir.herman.saper.controller;

import com.bsuir.herman.auth.dto.RegistrationRequestDto;
import com.bsuir.herman.auth.model.User;
import com.bsuir.herman.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @GetMapping("/")
    public String greeting() {
        return "Main";
    }

    private final UserService userService;

    @Autowired
    public MessageController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/api/v1/reg/create")
    public void create(@RequestBody RegistrationRequestDto requestDto) throws Exception {
        try {
            User user = requestDto.toUser();

            userService.register(user);

//            return ResponseEntity.ok();
        } catch (Exception e) {
            throw new Exception();
        }
    }

}
