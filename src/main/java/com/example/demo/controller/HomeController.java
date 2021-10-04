package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;

@Controller
@RequestMapping(value = "/user")
public class HomeController {

	@Autowired
	private IUserService uservice;
	
	@GetMapping(value = "/register")
	public String showRegistrationPage()
	{
		return "registration";
	}
	
	
	@PostMapping(value = "/register")
	public String saveData(@ModelAttribute User user,Model model)
	{
		String msg=uservice.saveUser(user);
		model.addAttribute("msg",msg);
		return "registration";
	}
	
	
	@GetMapping(value = "/login")
	public String showLoginPage()
	{
		return "login";
	}
	
	@GetMapping(value = "/access-denied")
	public String accessDeniedPage()
	{
		return "access-denied";
	}
	
}
