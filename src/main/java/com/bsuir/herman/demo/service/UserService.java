package com.bsuir.herman.demo.service;

import com.bsuir.herman.demo.model.auth.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
