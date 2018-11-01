package com.java.repository;

import java.util.Set;

import org.hibernate.Session;

import com.java.components.Address;
import com.java.components.Card;
import com.java.components.User;
import com.java.components.UserDetails;

public interface UserRepository {
	
	public void addUser(User user);
	
	public User getUser(String email);
	
	public void updateUser(User user);
	
	public UserDetails getUserDetails(long id);
	
	public void updateUserDetails(UserDetails userD, Session session);
	
	public void deleteUser(User user);

	Set<Address> getAddresses(User user);

	Set<Card> getCards(User user);
	
	
}
