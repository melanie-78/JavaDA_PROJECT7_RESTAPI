package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;

public interface SecurityService {
    User loadUserByUserName(String username);
}
