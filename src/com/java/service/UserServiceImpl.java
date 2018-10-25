package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.components.Cart;
import com.java.components.User;
import com.java.components.UserDetails;
import com.java.repository.UserRepositoryImpl;

@Service("userservice")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userrep")
	UserRepositoryImpl userRepository;

	@Override
	public void updateUser(User user) {
		userRepository.updateUser(user);
	}
	
	public void updateCart(User user, Cart cart) {
		userRepository.updateCart(user,cart);
	}

	@Override
	public void addUser(User user) {
		userRepository.addUser(user);
	}

	@Override
	public User getUser(String email) {
		return userRepository.getUser(email);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.deleteUser(user);
	}

}
