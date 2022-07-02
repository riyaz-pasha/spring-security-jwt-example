package com.example.springsecurityjwtexample.controller;

import com.example.springsecurityjwtexample.entity.Role;
import com.example.springsecurityjwtexample.entity.User;
import com.example.springsecurityjwtexample.request.UserRole;
import com.example.springsecurityjwtexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> saveUsers(User user) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(Role role) {
        var uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/assign")
    public ResponseEntity<?> assignRoleToUser(UserRole userRole) {
        userService.assignRoleToUser(userRole.getUserName(), userRole.getRoleName());
        return ResponseEntity.ok().build();
    }

}
