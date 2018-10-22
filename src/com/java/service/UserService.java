package com.java.service;

import com.java.components.User;
import com.java.components.UserDetails;

public interface UserService {

	public void updateUser(User user);
	
	public void addUser(User user);
	
	public User getUser(String email);
	
	public void deleteUser(User user);
	
}
