package com.bsuir.herman.auth.security;

import com.bsuir.herman.auth.model.User;
import com.bsuir.herman.auth.security.jwt.JwtUser;
import com.bsuir.herman.auth.security.jwt.JwtUserFactory;
import com.bsuir.herman.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        System.out.println("IN loadUserByUsername - user with username: {"+username+"} successfully loaded");
        return jwtUser;
    }
}