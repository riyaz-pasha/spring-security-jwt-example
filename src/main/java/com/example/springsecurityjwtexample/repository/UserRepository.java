package com.example.springsecurityjwtexample.repository;

import com.example.springsecurityjwtexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}
