package org.example.springpractice.user.dto;

import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String username;
    private final String password;

    public UserResponse(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
