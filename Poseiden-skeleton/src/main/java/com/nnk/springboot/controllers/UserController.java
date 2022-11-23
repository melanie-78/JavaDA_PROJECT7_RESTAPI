package com.nnk.springboot.controllers;

import com.nnk.springboot.ErrorResponse;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.util.ValidatePassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {

        log.info("GET /addUser");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            log.info("CREATE /validate with user {}", user);

            if(ValidatePassword.hasNumAndCap(user)&&ValidatePassword.hasSpecialCharacter(user)) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                user.setPassword(encoder.encode(user.getPassword()));
                userRepository.save(user);
                model.addAttribute("users", userRepository.findAll());
                return "redirect:/user/list";
            }else{
                ErrorResponse errorResponse = new ErrorResponse();
                String message = "Password must contain at least one uppercase letter, " +
                        "at least 8 characters, at least one number and one symbol ";
                errorResponse.setErrorMessage(message);
                model.addAttribute("errorResponse", errorResponse);
                return "redirect:/user/add";
            }
        }
        log.error("CREATE /validate error : {}",result);
        return "redirect:/user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("GET /showUpdateForm with id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("CREATE /updateUser error : {}",result);
            return "user/update";
        }
        log.info("CREATE /updateUser with id {}", id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        log.info("GET /deleteUser with id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/user/list";
    }
}
