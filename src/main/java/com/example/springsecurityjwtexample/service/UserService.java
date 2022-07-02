package com.example.springsecurityjwtexample.service;

import com.example.springsecurityjwtexample.domain.ROLE;
import com.example.springsecurityjwtexample.entity.Role;
import com.example.springsecurityjwtexample.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void assignRoleToUser(String userName, ROLE roleName);

    User getUser(String userName);

    List<User> getUsers();
}
