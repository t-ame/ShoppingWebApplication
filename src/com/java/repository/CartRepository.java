package com.java.repository;

import java.util.List;

import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.User;

public interface CartRepository {

	CartEntry getEntry(long id);

	CartEntry addToCart(User user, CartEntry entry);

	List<CartEntry> addEntriesToCart(User user, List<CartEntry> entries);

	void updateCart(User user, Cart cart);

	Cart getCart(User user);

	void updateEntry(CartEntry entry);

	void removeEntry(User user, CartEntry entry);

	Cart clearCart(User user);

}
