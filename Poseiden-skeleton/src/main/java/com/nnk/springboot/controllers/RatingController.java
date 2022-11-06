package com.nnk.springboot.controllers;

import com.nnk.springboot.util.CurrentUser;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import com.nnk.springboot.util.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class RatingController {
    // Inject Rating service
    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/rating/list")
    public String home(Model model, Authentication authentication)
    {
        log.info("GET /home with authentication {} ", authentication);
        CurrentUser currentUser = UserAuthentication.getUserAuthenticate(authentication);
        model.addAttribute("currentUser", currentUser);
        // find all Rating, add to model
        List<Rating> allRating = ratingService.getAllRating();
        model.addAttribute("listOfRating", allRating);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {

        log.info("GET /addRatingForm");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Rating list
        if (!result.hasErrors()) {
            log.info("CREATE /validate with rating {}", rating);
            ratingService.saveRating(rating);
            model.addAttribute("listOfRating", ratingService.getAllRating());
            return "redirect:/rating/list";
        }
        log.error("CREATE /validate error : {}",result);
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("GET /showUpdateForm with id {}", id);
        // get Rating by Id and to model then show to the form
        Rating rating = ratingService.getRatingById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                               BindingResult result, Model model) {
        // check required fields, if valid call service to update Rating and return Rating list
        if (result.hasErrors()) {
            log.error("CREATE /updateRating error : {}",result);
            return "rating/update";
        }
        log.info("CREATE /updateRating with id {}", id);
        rating.setId(id);
        ratingService.saveRating(rating);
        model.addAttribute("listOfRating", ratingService.getAllRating());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        log.info("GET /deleteRating with id {}", id);
        // Find Rating by Id and delete the Rating, return to Rating list
        ratingService.deleteRating(id);
        model.addAttribute("listOfRating", ratingService.getAllRating());
        return "redirect:/rating/list";
    }
}
