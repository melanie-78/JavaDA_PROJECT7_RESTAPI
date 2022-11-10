package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingServiceImplTest {
    @InjectMocks
    private RatingServiceImpl ratingService;

    @Mock
    private RatingRepository ratingRepository;

    @Test
    public void getAllRatingTest() {
        //GIVEN
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        Rating rating1 = new Rating("Moodys Rating1", "Sand PRating1", "Fitch Rating1", 20);

        List<Rating> list = new ArrayList<>();
        list.add(rating);
        list.add(rating1);

        when(ratingRepository.findAll()).thenReturn(list);

        //WHEN
        List<Rating> listResult = ratingService.getAllRating();

        //THEN
        assertTrue(listResult.size() == 2);
        verify(ratingRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void saveRatingTest(){
        //GIVEN
        Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        Rating expected = new Rating(1, "Moodys Rating", "Sand PRating", "Fitch Rating", 10 );
        when(ratingRepository.save(rating)).thenReturn(expected);

        //WHEN
        Rating savedRating = ratingService.saveRating(rating);

        //THEN
        verify(ratingRepository, Mockito.times(1)).save(rating);
        assertEquals(expected.getId(), savedRating.getId());
    }

    @Test
    public void deleteRatingTest(){
        //GIVEN
        Rating rating = new Rating(1,"Moodys Rating", "Sand PRating", "Fitch Rating", 10);
        Integer id = 1;

        when(ratingRepository.findById(id)).thenReturn(Optional.of(rating));
        doNothing().when(ratingRepository).delete(rating);

        //WHEN
        ratingService.deleteRating(id);

        //THEN
        verify (ratingRepository, Mockito.times(1)).findById(id);
        verify(ratingRepository,Mockito.times(1)).delete(rating);
    }

    @Test
    public void getRatingByIdTest(){
        //GIVEN
        Integer id = 1;
        Rating expected = new Rating(1, "Moodys Rating", "Sand PRating", "Fitch Rating", 10 );
        when(ratingRepository.findById(id)).thenReturn(Optional.of(expected));

        //WHEN
        Rating actual = ratingService.getRatingById(id);

        //THEN
        assertTrue(expected.getId() == 1);
    }
}
