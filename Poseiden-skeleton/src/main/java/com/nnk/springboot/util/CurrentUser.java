package com.nnk.springboot.util;

import com.nnk.springboot.util.AuthenticationMode;

public class CurrentUser {

    private String login;
    private AuthenticationMode mode;

    public CurrentUser() {
        this.mode = AuthenticationMode.CLASSIC;
    }

    public CurrentUser(String login, AuthenticationMode mode) {
        this.login = login;
        this.mode = mode;
    }

    public CurrentUser(String login) {
        this.login = login;
        this.mode =  AuthenticationMode.CLASSIC;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public AuthenticationMode getMode() {
        return mode;
    }

    public void setMode(AuthenticationMode mode) {
        this.mode = mode;
    }
}
