package com.nnk.springboot.controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    @Test
    @WithMockUser
    public void testHome() throws Exception {
        List<Rating> list = new ArrayList<>();

        when(ratingService.getAllRating()).thenReturn(list);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/rating/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("listOfRating"));
    }

    @Test
    @WithMockUser
    public void testAddRatingForm() throws Exception {
        Rating rating = new Rating();

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/rating/add"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testValidate() throws Exception {
        Rating rating = new Rating();
        Rating savedRating = new Rating();

        when(ratingService.saveRating(rating)).thenReturn(savedRating);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/rating/validate")
                        .param("moodysRating","Moodys Rating")
                        .param("sandPRating", "Sand Rating" )
                        .param("fitchRating", "Fitch Rating")
                        .param("orderNumber", "1"))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                //.andExpect(MockMvcResultMatchers.model().attributeExists("listOfRating"))
        ;
    }

    @Test
    @WithMockUser
    public void testShowUpdateForm() throws Exception {
        Integer id = 1;
        Rating rating = new Rating();

        when(ratingService.getRatingById(id)).thenReturn(rating);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/rating/update/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("rating"))
                .andExpect(MockMvcResultMatchers.view().name("rating/update"));
    }

    //@Test
    @WithMockUser
    public void testUpdateRating() throws Exception {
        Integer id = 1;
        Rating rating = new Rating();

        when(ratingService.saveRating(rating)).thenReturn(rating);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/rating/update/" + id)
                        .param("moodysRating","Moodys Rating")
                        .param("sandPRating", "Sand Rating" )
                        .param("fitchRating", "Fitch Rating"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rating/list"))
                //.andExpect(MockMvcResultMatchers.model().attributeExists("listOfRating"))
        ;
    }

    @Test
    @WithMockUser
    public void testDeleteRating() throws Exception {
        Integer id = 1;
        Rating rating = new Rating();

        doNothing().when(ratingService).deleteRating(id);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/rating/delete/"+id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/rating/list"));
    }
}
