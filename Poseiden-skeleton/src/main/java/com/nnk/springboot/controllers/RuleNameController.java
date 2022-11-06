package com.nnk.springboot.controllers;

import com.nnk.springboot.util.CurrentUser;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
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
public class RuleNameController {
    // Inject RuleName service
    private RuleNameService ruleNameService;

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model, Authentication authentication)
    {   log.info("GET /home with authentication {} ", authentication);
        CurrentUser currentUser = UserAuthentication.getUserAuthenticate(authentication);
        model.addAttribute("currentUser", currentUser);
        // find all RuleName, add to model
        List<RuleName> allRuleName = ruleNameService.getAllRuleName();
        model.addAttribute("listOfRuleName",allRuleName);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        log.info("GET /addRuleForm");
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // check data valid and save to db, after saving return RuleName list
        if (!result.hasErrors()) {
            log.info("CREATE /validate with ruleName {}", ruleName);
            ruleNameService.saveRuleName(ruleName);
            model.addAttribute("listOfRuleName", ruleNameService.getAllRuleName());
            return "redirect:/ruleName/list";
        }
        log.error("CREATE /validate error : {}",result);
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("GET /showUpdateForm with id {}", id);
        // get RuleName by Id and to model then show to the form
        RuleName ruleName = ruleNameService.getRuleNameById(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        // check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            log.error("CREATE /updateRuleName error : {}",result);
            return "ruleName/update";
        }
        log.info("CREATE /updateRuleName with id {}", id);
        ruleName.setId(id);
        ruleNameService.saveRuleName(ruleName);
        model.addAttribute("listOfRuleName", ruleNameService.getAllRuleName());
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        log.info("GET /deleteRuleName with id {}", id);
        // Find RuleName by Id and delete the RuleName, return to Rule list
        ruleNameService.deleteRuleName(id);
        model.addAttribute("listOfRuleName", ruleNameService.getAllRuleName());
        return "redirect:/ruleName/list";
    }
}
