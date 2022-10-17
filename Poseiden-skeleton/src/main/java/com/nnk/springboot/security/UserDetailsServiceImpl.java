package com.nnk.springboot.security;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userApp = securityService.loadUserByUserName(username);

        Collection<GrantedAuthority> authorities= new ArrayList<>();
        
        SimpleGrantedAuthority authority= new SimpleGrantedAuthority(userApp.getRole());
        authorities.add(authority);

        return new org.springframework.security.core.userdetails.User(userApp.getUsername(), userApp.getPassword(), authorities);
    }
}
