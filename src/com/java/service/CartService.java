package com.java.service;

import java.util.List;

import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.User;

public interface CartService {

	CartEntry getEntry(long id);

	CartEntry addToCart(User user, CartEntry entry);

	void updateCart(User user, Cart cart);

	List<CartEntry> addEntriesToCart(User user, List<CartEntry> entries);

	Cart getCart(User user);

	void removeEntry(User user, CartEntry entry);

	Cart clearCart(User user);

	void updateEntry(CartEntry entry);

}
