package com.bsuir.herman.demo.service.impl;

import com.bsuir.herman.demo.model.auth.Role;
import com.bsuir.herman.demo.model.auth.Status;
import com.bsuir.herman.demo.model.auth.User;
import com.bsuir.herman.demo.repository.RoleRepository;
import com.bsuir.herman.demo.repository.UserRepository;
import com.bsuir.herman.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        System.out.println("IN register - user: {"+registeredUser+"} successfully registered");

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        System.out.println("IN getAll - {"+result.size()+"} users found");
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        System.out.println("IN findByUsername - user: {"+result+"} found by username: {"+username+"}");
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            System.out.println("IN findById - no user found by id: {"+id+"}");
            return null;
        }

        System.out.println("IN findById - user: {"+result+"} found by id: {}");
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        System.out.println("IN delete - user with id: {"+id+"} successfully deleted");
    }
}
