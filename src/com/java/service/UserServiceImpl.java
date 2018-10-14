package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.repository.UserRepositoryImpl;

@Service("userservice")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userrep")
	UserRepositoryImpl userRepository;

}
