package com.bsuir.herman.auth.dto;

import lombok.Data;

@Data
public class LoginResponseDto {

    private String status;

    private Long userId;

    private String username;

    private String token;

    private String description;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String status, Long userId, String username, String token, String description) {
        this.status = status;
        this.userId = userId;
        this.username = username;
        this.token = token;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
