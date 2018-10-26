package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.components.Address;
import com.java.components.Cart;
import com.java.components.Order;
import com.java.components.User;
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
	
	public void updateAddress(User user, Address address) {
		userRepository.updateAddress(user,address);
	}
	
//	public void updateUser(User user, Cart cart, Address address, List<Order> orders) {
//		userRepository.updateUser( user, cart, address, orders);
//	}
	
	public void updateOrders(User user, List<Order> orders) {
		userRepository.updateOrders( user, orders);
	}
	
	public void updateUser(User user, Address address) {
		userRepository.updateUser(user, address);
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
