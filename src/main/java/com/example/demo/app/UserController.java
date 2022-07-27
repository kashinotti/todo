package com.example.demo.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
//	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("signup")
	public String signup() {
		return "signup";
	}
	
	@PostMapping("create")
	public String create(@ModelAttribute @Validated User user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
	        }
	        model.addAttribute("validationError", errorList);
	        return "signup";
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
		userService.save(user);
		
		return "redirect:/login";
	}
	
	@GetMapping("edit")
	public String edit(Model model, @ModelAttribute User loginUser) {
		User loginUserInfo = userService.findByUsername(loginUser.getUsername());
		model.addAttribute("loginUser", loginUserInfo);
		return "edit";
	}
	
	@PostMapping("update")
	//@ResponseBody
	public String update(@ModelAttribute User loginUser, Model model) {
//		if(result.hasErrors()) {
//			List<String> errorList = new ArrayList<String>();
//			for (ObjectError error : result.getAllErrors()) {
//				errorList.add(error.getDefaultMessage());
//	        }
//	        model.addAttribute("validationError", errorList);
//	        return "redirect:/user/edit";
//		}
		
		if(loginUser.getPassword() != "" ) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodePassword = passwordEncoder.encode(loginUser.getPassword());
			loginUser.setPassword(encodePassword);
			userService.save(loginUser);
		} else {
			User oldUser = userService.findById(loginUser.getId());
			loginUser.setPassword(oldUser.getPassword());
			userService.save(loginUser);
		}
		updateSecurity(loginUser);
		return "redirect:/";
	}
	
	//ユーザー情報変更後に認証情報とセッションで保持している値を変更するメソッド
	private void updateSecurity(User user) {
		//EntityのUserとクラス名がかぶるので完全修飾名で記載
		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
		//セッションの値をセット
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
	}
}
