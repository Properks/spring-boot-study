package com.example.stomp.security.dto;

import lombok.Getter;

/**
 * The request DTO to add user (sign up)
 */
@Getter
public class AddUserRequest {
    private final String email;
    private final String password;
    private final String nickname;

    /**
     * The Constructor
     *
     * @param email The email of user
     * @param password The password of user
     * @param nickname The nickname of user
     */
    public AddUserRequest(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
