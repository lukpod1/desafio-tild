package com.tild.desafio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tild.desafio.blog.model.User;
import com.tild.desafio.services.UserService;
import com.tild.desafio.blog.data.UserRepository;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/form")
public class UserController {
	
	private UserRepository userRepository;
	private UserService userService;

	
	public UserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public ModelAndView newUser() {
		ModelAndView mv = new ModelAndView("form");
		
		mv.addObject("newUser", new User());
		
		return mv;
	}
	
	@PostMapping
	public ModelAndView createUser(User user) {
		if(user.isValid()) {
			userRepository.save(user);
		}
		
		return new ModelAndView("redirect:/");
	}
	
	@PostMapping("/save")
	public String save(User user) {
		System.out.println(user);
		userService.save(user);
		
		return "redirect:/";
	}
	
	
}
