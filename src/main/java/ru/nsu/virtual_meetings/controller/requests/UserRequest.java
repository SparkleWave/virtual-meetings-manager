package ru.nsu.virtual_meetings.controller.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {

    public UserRequest(){}

    @JsonProperty(value = "userId")
    private Long userId;

    @JsonProperty(value = "userLogin")
    private String userLogin;

    @JsonProperty(value = "userPassword")
    private String userPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
