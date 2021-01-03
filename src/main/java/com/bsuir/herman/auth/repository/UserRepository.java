package com.bsuir.herman.auth.repository;

import com.bsuir.herman.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);

    User findByEmail (String email);
}
