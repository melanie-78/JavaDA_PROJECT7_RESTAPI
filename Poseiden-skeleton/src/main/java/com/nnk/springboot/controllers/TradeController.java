package com.nnk.springboot.controllers;

import com.nnk.springboot.util.CurrentUser;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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
public class TradeController {
    // Inject Trade service
    private TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @RequestMapping("/trade/list")
    public String home(Model model, Authentication authentication) {
        log.info("GET /home with authentication {} ", authentication);
        CurrentUser currentUser = UserAuthentication.getUserAuthenticate(authentication);
        model.addAttribute("currentUser", currentUser);
        // find all Trade, add to model
        List<Trade> allTrade = tradeService.getAllTrade();
        model.addAttribute("listOfTrade", allTrade);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade bid) {

        log.info("GET /addTrade");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Trade list
        if(!result.hasErrors()){
            log.info("CREATE /validate with trade {}", trade);
            tradeService.saveTrade(trade);
            model.addAttribute("listOfTrade",tradeService.getAllTrade());
        }
        log.error("CREATE /validate error : {}",result);
        return "trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("GET /showUpdateForm with id {}", id);
        // get Trade by Id and to model then show to the form
        Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {
        // check required fields, if valid call service to update Trade and return Trade list
        if (result.hasErrors()) {
            log.error("CREATE /updateTrade error : {}",result);
            return "trade/update";
        }
        log.info("CREATE /updateTrade with id {}", id);
        trade.setTradeId(id);
        tradeService.saveTrade(trade);
        model.addAttribute("listOfTrade", tradeService.getAllTrade());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        log.info("GET /deleteTrade with id {}", id);
        // Find Trade by Id and delete the Trade, return to Trade list
        tradeService.deleteTrade(id);
        model.addAttribute("listOfTrade", tradeService.getAllTrade());
        return "redirect:/trade/list";
    }
}
