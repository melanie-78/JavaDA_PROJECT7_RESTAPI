package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SecurityServiceImpl implements SecurityService{
    private UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUserName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new NoSuchElementException("This user does'nt exist in database"));
        return user;
    }
}
