package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "Mel", authorities = {"ADMIN"})
    public void testHome() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/user/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "Mel", authorities = {"ADMIN"})
    public void testAddUser() throws Exception {
        User user = new User();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/user/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    //@Test
    @WithMockUser(username = "Mel", authorities = {"ADMIN"})
    public void testValidate() throws Exception {
        User user = new User();
        User savedUser = new User();

        when(userRepository.save(user)).thenReturn(savedUser);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/user/validate"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "Mel", authorities = {"ADMIN"})
    public void testShowUpdateForm() throws Exception {
        Integer id = 1;
        User user = new User();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/user/update/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andExpect(MockMvcResultMatchers.view().name("user/update"));
    }

    //@Test
    @WithMockUser(username = "Mel", authorities = {"ADMIN"})
    public void testUpdateUser() throws Exception {
        Integer id = 1;
        User user = new User();
        User savedUser = new User();

        when(userRepository.save(user)).thenReturn(savedUser);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/user/update/" + id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "Mel", authorities = {"ADMIN"})
    public void testDeleteUser() throws Exception {
        Integer id = 1;
        User user = new User();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        doNothing().when(userRepository).delete(user);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/user/delete/"+id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/list"));
    }
}
