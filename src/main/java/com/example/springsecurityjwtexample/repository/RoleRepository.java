package com.example.springsecurityjwtexample.repository;

import com.example.springsecurityjwtexample.domain.ROLE;
import com.example.springsecurityjwtexample.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(ROLE role);

}
