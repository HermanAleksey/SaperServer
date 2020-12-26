package com.bsuir.herman.demo.repository;

import com.bsuir.herman.demo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
