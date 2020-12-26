package com.bsuir.herman.demo.repository;

import com.bsuir.herman.demo.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
