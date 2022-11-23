package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{
    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * @return the list of all rating saved in database
     */
    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    /**
     * @param id identifier of the rating we want to delete
     */
    @Override
    public void deleteRating(Integer id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id: "+id));
        ratingRepository.delete(rating);
    }

    /**
     * @param rating a rating type object
     * @return rating saved in database with an identifier
     */
    @Override
    public Rating saveRating(Rating rating) {
        Rating savedRating= ratingRepository.save(rating);
        return savedRating;
    }

    /**
     * @param id identifier of a rating we want to get
     * @return a rating object saved in database if existing
     */

    @Override
    public Rating getRatingById(Integer id) {
        Rating ratingById = ratingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id: "+ id));
        return ratingById;
    }
}
