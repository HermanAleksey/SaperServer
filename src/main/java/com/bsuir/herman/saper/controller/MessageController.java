package com.bsuir.herman.saper.controller;

import com.bsuir.herman.auth.dto.AuthenticationRequestDto;
import com.bsuir.herman.auth.dto.MessageDto;
import com.bsuir.herman.auth.dto.RegistrationRequestDto;
import com.bsuir.herman.auth.model.User;
import com.bsuir.herman.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class MessageController {

    @GetMapping("/token")
    public AuthenticationRequestDto tokenModel() {
        AuthenticationRequestDto dto = new AuthenticationRequestDto();
        dto.setUsername("Username");
        dto.setPassword("Password");
        return dto;
    }

    @GetMapping("/")
    public MessageDto mains() {
        System.out.println("In main test function!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return new MessageDto("Main");
    }

    @GetMapping("/hello")
    public String greeting(@RequestParam (name = "DEFAULT") String name) {
        return "Hello, "+name;
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
