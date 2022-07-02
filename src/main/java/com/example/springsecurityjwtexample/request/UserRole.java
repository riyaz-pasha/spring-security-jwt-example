package com.example.springsecurityjwtexample.request;

import lombok.Value;

@Value
public class UserRole {
    private String userName;
    private String roleName;
}
