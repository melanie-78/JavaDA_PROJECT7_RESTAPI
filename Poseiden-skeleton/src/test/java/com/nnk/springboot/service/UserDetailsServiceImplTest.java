package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.security.UserDetailsServiceImpl;
import com.nnk.springboot.service.SecurityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private SecurityServiceImpl securityService;

    @Test
    public void loadUserByUsernameTest(){
        //GIVEN
        String username = "Mel";
        String pwd = new String();

        User userApp = new User(null, "Mel", pwd, "Melanie", "ADMIN");
        when(securityService.loadUserByUserName(username)).thenReturn(userApp);

        //WHEN
        UserDetails actual = userDetailsService.loadUserByUsername(username);

        //THEN
        //verify(passwordEncoder, Mockito.times(1)).encode(pwdMel);
        verify(securityService, Mockito.times(1)).loadUserByUserName(username);
        assertEquals(userApp.getUsername(), actual.getUsername());
        assertEquals(userApp.getPassword(), actual.getPassword());
    }
}
