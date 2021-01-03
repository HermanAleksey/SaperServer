package com.bsuir.herman.auth.rest;

import com.bsuir.herman.auth.dto.AuthenticationRequestDto;
import com.bsuir.herman.auth.dto.RegistrationRequestDto;
import com.bsuir.herman.auth.model.User;
import com.bsuir.herman.auth.security.jwt.JwtTokenProvider;
import com.bsuir.herman.auth.service.UserService;
import com.bsuir.herman.auth.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        return authenticate(requestDto.getUsername(), requestDto.getPassword());
    }

    @PostMapping("registration")
    public ResponseEntity create(@RequestBody RegistrationRequestDto requestDto) {
        try {
            //registration
            register(requestDto);

            //authorization
            return authenticate(requestDto.getUsername(), requestDto.getPassword());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    private ResponseEntity authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
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
        if (!validator.matchEmail(userEmail)) return false;
        if (!validator.matchPassword(userPassword)) return false;
        if (!validator.matchNickname(userUsername)) return false;

        try {
            //Проверка, не существует ли уже данный пользователь
            if (userService.findByUsername(userUsername) == null) return false;
            if (userService.findByEmail(userEmail) == null) return false;
            //добавление нового пользователя
            userService.register(userForm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
