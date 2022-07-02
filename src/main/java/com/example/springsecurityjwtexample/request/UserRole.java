package com.example.springsecurityjwtexample.request;

import com.example.springsecurityjwtexample.domain.ROLE;
import lombok.Value;

@Value
public class UserRole {
    private String userName;
    private ROLE roleName;
}
