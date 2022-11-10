package com.nnk.springboot.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class UserAuthentication {
    public static CurrentUser getUserAuthenticate(Authentication authentication){
        CurrentUser currentUser = new CurrentUser();

        if(authentication instanceof OAuth2AuthenticationToken) {
            DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
            String login = (String) user.getAttributes().get("login");
            currentUser.setMode(AuthenticationMode.GITHUB);
            currentUser.setLogin(login);
        }
        else if(authentication instanceof UsernamePasswordAuthenticationToken){
            String login = ((User) authentication.getPrincipal()).getUsername();
            currentUser.setLogin(login);
        }
        return currentUser;
    }
}
