package com.bsuir.herman.auth.controller;

import com.bsuir.herman.auth.Debug;
import com.bsuir.herman.auth.dto.AuthenticationRequestDto;
import com.bsuir.herman.auth.dto.LoginResponseDto;
import com.bsuir.herman.auth.dto.RegistrationRequestDto;
import com.bsuir.herman.auth.model.User;
import com.bsuir.herman.auth.security.jwt.JwtTokenProvider;
import com.bsuir.herman.auth.service.UserService;
import com.bsuir.herman.auth.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody AuthenticationRequestDto requestDto) {
        Debug.printMapping("/api/v1/auth/login");
        return authenticate(requestDto.getUsername(), requestDto.getPassword());
    }

    @PostMapping("registration")
    public ResponseEntity<LoginResponseDto> create(@RequestBody RegistrationRequestDto requestDto) {
        Debug.printMapping("/api/v1/auth/registration");
        try {
            //registration
            if (!register(requestDto)) {
                LoginResponseDto responseDto = new LoginResponseDto(
                        "Error.", -1L, "", "", "Invalid value.");
//                Map<Object, Object> response = new HashMap<>();
//                response.put("status", "Error.");
//                response.put("userId", -1);
//                response.put("username", "");
//                response.put("token", "");
//                response.put("description", "Invalid value.");
                return ResponseEntity.ok(responseDto);
            }

            //authorization
            return authenticate(requestDto.getUsername(), requestDto.getPassword());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private ResponseEntity<LoginResponseDto> authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userService.findByUsername(username);

            if (user == null) {
                LoginResponseDto responseDto = new LoginResponseDto(
                        "Error.", -1L, "", "", "User with username: " + username + " not found.");
//                Map<Object, Object> response = new HashMap<>();
//                response.put("status", "Error.");
//                response.put("userId", -1);
//                response.put("username", "");
//                response.put("token", "");
//                response.put("description", "User with username: \" + username + \" not found");

                return ResponseEntity.ok(responseDto);
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            LoginResponseDto responseDto = new LoginResponseDto(
                    "Success.", user.getId(), username, token, "");

//            Map<Object, Object> response = new HashMap<>();
//            response.put("status", "Success.");
//            response.put("userId", user.getId());
//            response.put("username", username);
//            response.put("token", token);
//            response.put("description", "");

            return ResponseEntity.ok(responseDto);
        } catch (AuthenticationException e) {
            LoginResponseDto responseDto = new LoginResponseDto(
                    "Error.", -1L, "", "", "Invalid username or password.");
//            Map<Object, Object> response = new HashMap<>();
//            response.put("status", "Error.");
//            response.put("userId", -1);
//            response.put("username", "");
//            response.put("token", "");
//            response.put("description", "Invalid username or password");

            return ResponseEntity.ok(responseDto);
        }
    }

    private boolean register(RegistrationRequestDto registrationDto) {
        //Проверка сущности на соответстввие
        User userForm = registrationDto.toUser();

        String userEmail = userForm.getEmail();
        String userPassword = userForm.getPassword();
        String userUsername = userForm.getUsername();

        //Проверка на корректность вводимых значений
        Validator validator = new Validator();
        if (!validator.matchEmail(userEmail)) {
            System.out.println("Email " + userEmail + " doesn't suit");
            return false;
        }
        if (!validator.matchPassword(userPassword)) {
            System.out.println("Password " + userPassword + " doesn't suit");
            return false;
        }
        if (!validator.matchNickname(userUsername)) {
            System.out.println("Nickname " + userUsername + " doesn't suit");
            return false;
        }

        try {
            //Проверка, не существует ли уже данный пользователь
            if (userService.findByUsername(userUsername) != null) return false;
            if (userService.findByEmail(userEmail) != null) return false;
            //добавление нового пользователя
            userService.register(userForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
