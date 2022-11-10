package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String pwd = passwordEncoder.encode("Melanie8!");
        String pwd1 = passwordEncoder.encode("Franck8!");

        User melAdmin = new User(null, "Mel", pwd, "Melanie", "ADMIN");
        User franckUser = new User(null, "Franck", pwd1, "Franck Aurelien", "USER");

        userRepository.save(melAdmin);
        userRepository.save(franckUser);
    }
}
