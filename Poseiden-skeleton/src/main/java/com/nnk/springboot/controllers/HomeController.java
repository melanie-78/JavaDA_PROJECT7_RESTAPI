package com.nnk.springboot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController
{
	@RequestMapping("/")
	public String home(Model model, Authentication authentication)
	{
		String name = authentication.getName();
		DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
		String login = (String) user.getAttributes().get("login");
		String a = "toto";
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}


}
