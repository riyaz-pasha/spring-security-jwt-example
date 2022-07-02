package com.example.springsecurityjwtexample;

import com.example.springsecurityjwtexample.domain.ROLE;
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
            userService.saveRole(new Role(null, ROLE.ADMIN));
            userService.saveRole(new Role(null, ROLE.MANAGER));
            userService.saveRole(new Role(null, ROLE.SUPER_ADMIN));
            userService.saveRole(new Role(null, ROLE.USER));

            userService.saveUser(new User(null, "Name 1", "username1", "password1", emptyList()));
            userService.saveUser(new User(null, "Name 2", "username2", "password2", emptyList()));
            userService.saveUser(new User(null, "Name 3", "username3", "password3", emptyList()));
            userService.saveUser(new User(null, "Name 4", "username4", "password4", emptyList()));

            userService.assignRoleToUser("username1", ROLE.ADMIN);
            userService.assignRoleToUser("username1", ROLE.SUPER_ADMIN);
        };
    }

}
