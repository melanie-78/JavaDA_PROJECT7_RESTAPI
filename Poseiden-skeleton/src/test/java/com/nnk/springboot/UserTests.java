package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserTests {
    @Autowired
    private UserRepository userRepository;

    //@Test
    public void UserTest(){
        User user = new User("Luna", "pipi", "Luna Evy", "USER");

        //Save
        user = userRepository.save(user);
        Assert.assertNotNull(user.getId());
        assertTrue(user.getUsername().equals("Luna"));

        // Update
        user.setFullname("Luna Adriel");
        user = userRepository.save(user);
        assertTrue(user.getFullname().equals("Luna Adriel"));

        // Find
        List<User> listResult = userRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Integer id = user.getId();
        userRepository.delete(user);
        Optional<User> userList = userRepository.findById(id);
        assertFalse(userList.isPresent());
    }

}
