package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.User;
import com.java.repository.CartRepositoryImpl;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepositoryImpl cartRep;
	
	@Override
	public CartEntry getEntry(long id) {
		return cartRep.getEntry(id);
	}

	@Override
	public void updateEntry(CartEntry entry) {
		cartRep.updateEntry(entry);
	}

	@Override
	public Cart getCart(User user) {
		return cartRep.getCart(user);
	}
	@Override
	public void removeEntry(User user, CartEntry entry) {
		cartRep.removeEntry(user, entry);
	}
	
	@Override
	public Cart clearCart(User user) {
		return cartRep.clearCart(user);
	}
	
	@Override
	public CartEntry addToCart(User user, CartEntry entry) {
		return cartRep.addToCart(user, entry);
	}

	@Override
	public List<CartEntry> addEntriesToCart(User user, List<CartEntry> entries) {
		return cartRep.addEntriesToCart(user, entries);
	}
	
	@Override
	public void updateCart(User user, Cart cart) {
		cartRep.updateCart(user, cart);
	}
}
