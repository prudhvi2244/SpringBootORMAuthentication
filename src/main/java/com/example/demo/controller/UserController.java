package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping(value = "/welcome")
	public String welcomePage(Principal p,Model m)
	{
		m.addAttribute("username",p.getName());
		return "user/welcome";
	}
	
	@GetMapping(value = "/admin")
	public String adminPage()
	{
		return "user/admin";
	}
	
	
	@GetMapping(value = "/employee")
	public String employeenPage()
	{
		return "user/employee";
	}
	
	@GetMapping(value = "/student")
	public String studentPage()
	{
		return "user/student";
	}
	
	
}
