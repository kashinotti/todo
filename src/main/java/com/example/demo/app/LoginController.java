package com.example.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("login")
@Controller
public class LoginController {
	
	@GetMapping
	public String login() {
		return "login";
	}
	
//	@PostMapping
//	public String login(@ModelAttribute("username") String username, 
//			@RequestAttribute(name = WebAttributes.AUTHENTICATION_EXCEPTION, required = false) Exception exception, 
//			Model model) {
//		
//		model.addAttribute("message", username);
//		System.out.println(username);
//		
//		if (exception != null) {
//		      model.addAttribute("message", exception.getMessage());
//		}
//		
//		return "login";
//	}
}
