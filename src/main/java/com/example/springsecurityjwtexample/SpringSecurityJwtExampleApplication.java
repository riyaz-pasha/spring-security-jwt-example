package com.example.springsecurityjwtexample;

import com.example.springsecurityjwtexample.entity.Role;
import com.example.springsecurityjwtexample.entity.User;
import com.example.springsecurityjwtexample.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static java.util.Collections.emptyList;

@SpringBootApplication
public class SpringSecurityJwtExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            userService.saveUser(new User(null, "Name 1", "USERNAME 1", "PASSWORD1", emptyList()));
            userService.saveUser(new User(null, "Name 2", "USERNAME 2", "PASSWORD2", emptyList()));
            userService.saveUser(new User(null, "Name 3", "USERNAME 3", "PASSWORD3", emptyList()));
            userService.saveUser(new User(null, "Name 4", "USERNAME 4", "PASSWORD4", emptyList()));

            userService.assignRoleToUser("USERNAME 1", "ROLE_USER");
            userService.assignRoleToUser("USERNAME 1", "ROLE_MANAGER");
        };
    }

}
