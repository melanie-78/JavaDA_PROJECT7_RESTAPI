package com.nnk.springboot.controllers;

import com.nnk.springboot.util.CurrentUser;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.util.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class CurveController {
    // Inject Curve Point service
    private CurvePointService curvePointService;

    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model, Authentication authentication)
    {
        log.info("GET /home with authentication {} ", authentication);
        CurrentUser currentUser = UserAuthentication.getUserAuthenticate(authentication);
        model.addAttribute("currentUser", currentUser);
        // find all Curve Point, add to model
        List<CurvePoint> allCurvePoint = curvePointService.getAllCurvePoint();
        model.addAttribute("listOfCurvePoint", allCurvePoint);
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint bid)
    {
        log.info("GET /addCurvePointForm");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // check data valid and save to db, after saving return Curve list
        if (!result.hasErrors()) {
            log.info("CREATE /validate with curvePoint {}", curvePoint);
            curvePointService.saveCurvePoint(curvePoint);
            model.addAttribute("listOfCurvePoint", curvePointService.getAllCurvePoint());
            return "redirect:/curvePoint/list";
        }
        log.error("CREATE /validate error : {}",result);
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("GET /showUpdateForm with id {}", id);
        // get CurvePoint by Id and to model then show to the form
        CurvePoint curvePoint = curvePointService.getCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                                   BindingResult result, Model model) {
        // check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            log.error("CREATE /updateCurvePoint error : {}",result);
            return "curvePoint/update";
        }
        log.info("CREATE /updateCurvePoint with id {}", id);
        curvePoint.setId(id);
        curvePointService.saveCurvePoint(curvePoint);
        model.addAttribute("listOfCurvePoint", curvePointService.getAllCurvePoint());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        log.info("GET /deleteCurvePoint with id {}", id);
        // Find Curve by Id and delete the Curve, return to Curve list
        curvePointService.deleteCurvePoint(id);
        model.addAttribute("listOfCurvePoint", curvePointService.getAllCurvePoint());
        return "redirect:/curvePoint/list";
    }
}
