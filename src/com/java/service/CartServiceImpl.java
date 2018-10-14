package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.repository.CartRepositoryImpl;

@Service("cartservice")
public class CartServiceImpl implements CartService {

	@Autowired
	@Qualifier("cartrep")
	CartRepositoryImpl cartRepository;

}
