package com.bsuir.herman.auth.service;

import com.bsuir.herman.auth.model.User;

import java.util.List;

public interface UserService {

    void register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findByEmail(String login);

    User findById(Long id);

    void delete(Long id);
}
