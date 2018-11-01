package com.java.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.components.Address;
import com.java.components.Card;
import com.java.components.Order;
import com.java.components.User;
import com.java.repository.AddressRepositoryImpl;
import com.java.repository.UserRepositoryImpl;

@Service("userservice")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userrep")
	UserRepositoryImpl userRepository;
	
	@Autowired
	AddressRepositoryImpl addressRep;

	@Override
	public void updateUser(User user) {
		userRepository.updateUser(user);
	}
	
	@Override
	public void updateAddress(User user, Address address) {
		userRepository.updateAddress(user,address);
	}

	@Override
	public void updateOrders(User user, List<Order> orders) {
		userRepository.updateOrders( user, orders);
	}

	@Override
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

	@Override
	public Set<Card> getCards(User user){
		return userRepository.getCards(user);
	}

	@Override
	public Set<Address> getAddresses(User user){
		return userRepository.getAddresses(user);
	}
	
}
