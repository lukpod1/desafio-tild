package com.tild.desafio.services;

import org.springframework.stereotype.Service;

import com.tild.desafio.blog.data.UserRepository;
import com.tild.desafio.blog.model.User;

@Service
public class UserServiceImp implements UserService{
	
	private UserRepository userRepository;
	
	public UserServiceImp(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}

	@Override
	public void save(User user) {
		this.userRepository.save(user);
		
	}
	
	
}
