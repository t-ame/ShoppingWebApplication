package com.java.service;

import java.util.List;
import java.util.Set;

import com.java.components.Address;
import com.java.components.Card;
import com.java.components.Order;
import com.java.components.User;

public interface UserService {

	public void updateUser(User user);
	
	public void addUser(User user);
	
	public User getUser(String email);
	
	public void deleteUser(User user);

	void updateAddress(User user, Address address);

	void updateOrders(User user, List<Order> orders);

	void updateUser(User user, Address address);

	Set<Card> getCards(User user);

	Set<Address> getAddresses(User user);

}
