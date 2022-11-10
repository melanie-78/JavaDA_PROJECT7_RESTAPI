package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceImplTest {
    @InjectMocks
    private SecurityServiceImpl securityService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void loadUserByUsernameTest(){
        //GIVEN
        String username = "Melanie";
        String pwd = new String();
        User user = new User("Mel", pwd, "Melanie", "ADMIN");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        //WHEN
        User actual = securityService.loadUserByUserName(username);

        //THEN
        verify(userRepository, Mockito.times(1)).findByUsername(username);
        assertEquals(actual.getFullname(), user.getFullname());

    }
}
