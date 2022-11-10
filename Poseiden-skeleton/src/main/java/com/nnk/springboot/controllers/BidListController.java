package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.util.CurrentUser;
import com.nnk.springboot.service.BidListService;
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


@Controller
@Slf4j
@RequestMapping("")
public class BidListController {
    // Inject Bid service
    private BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model,Authentication authentication) {
        log.info("GET /home with authentication {} ", authentication);
        CurrentUser currentUser = UserAuthentication.getUserAuthenticate(authentication);
        model.addAttribute("currentUser", currentUser);

        //call service find all bids to show to the view
        List<BidList> allBidLists = bidListService.getAllBidList();
        model.addAttribute("listOfBid", allBidLists);

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        log.info("GET /addBidForm");
        return "bidList/add";
    }
    //check data valid and save to db, after saving return bid list
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            log.info("CREATE /validate with bid {}", bid);
            bidListService.saveBid(bid);
            model.addAttribute("listOfBid", bidListService.getAllBidList());
            return "redirect:/bidList/list";
        }
        log.error("CREATE /validate error : {}",result);
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("GET /showUpdateForm with id {}", id);
        // get Bid by Id and to model then show to the form
        BidList bidList = bidListService.getBid(id);
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        // check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            log.error("CREATE /updateBid error : {}",result);
            return "bidList/update";
        }
        log.info("CREATE /updateBid with id {}", id);
        bidList.setBidListId(id);
        bidListService.saveBid(bidList);
        model.addAttribute("listOfBib", bidListService.getAllBidList());
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        log.info("GET /deleteBid with id {}", id);
        // Find Bid by Id and delete the bid, return to Bid list
        bidListService.deleteBid(id);
        model.addAttribute("bidList", bidListService.getAllBidList());
        return "redirect:/bidList/list";
    }
}
