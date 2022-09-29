package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> getAllRating();
    void deleteRating(Integer id);
    Rating saveRating(Rating rating);
    Rating getRatingById(Integer id);
}
