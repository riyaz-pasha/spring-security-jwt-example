package com.example.springsecurityjwtexample.service;

import com.example.springsecurityjwtexample.entity.Role;
import com.example.springsecurityjwtexample.entity.User;
import com.example.springsecurityjwtexample.repository.RoleRepository;
import com.example.springsecurityjwtexample.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the DB", user.getUserName());
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the DB", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void assignRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByName(roleName);
        log.info("Assigning role {} to the user {}", roleName, userName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userName) {
        log.info("Fetching user {}", userName);
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }
}
