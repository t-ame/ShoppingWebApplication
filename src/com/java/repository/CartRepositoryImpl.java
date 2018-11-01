package com.java.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.components.Cart;
import com.java.components.CartEntry;
import com.java.components.User;
import com.java.components.UserDetails;

@Repository
public class CartRepositoryImpl implements CartRepository {

	@Autowired
	SessionFactory sf;

	@Override
	public CartEntry getEntry(long id) {
		Session session = sf.openSession();
		CartEntry entry = session.get(CartEntry.class, id);
		session.close();
		return entry;
	}

	@Override
	public void updateEntry(CartEntry entry) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(entry);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void removeEntry(User user, CartEntry entry) {
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		Cart cart = user.getUserDetails().getCart();
		cart.removeFromCart(entry);
		session.delete(entry);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public Cart getCart(User user) {
		Cart cart = null;
		Session session = sf.openSession();
		cart = session.get(User.class, user.getUserEmail()).getUserDetails().getCart();
		session.close();
		return cart;
	}
	
	@Override
	public Cart clearCart(User user) {
		Cart cart = null;
		Session session = sf.openSession();
		session.beginTransaction();
		cart = session.get(User.class, user.getUserEmail()).getUserDetails().getCart();
		List<CartEntry> entries = cart.getCartEntries();
		cart.setCartEntries(new ArrayList<>());;
		for(CartEntry entry : entries) {
			session.delete(entry);
		}
		session.getTransaction().commit();
		session.close();
		return cart;
	}

	@Override
	public CartEntry addToCart(User user, CartEntry entry) {
		entry.setCartEntryId(0);
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		UserDetails ud = user.getUserDetails();
		if (ud != null) {
			ud.addCartEntry(entry);
		}
		session.update(user);
		session.getTransaction().commit();
		session.close();
		return entry;
	}

	@Override
	public List<CartEntry> addEntriesToCart(User user, List<CartEntry> entries) {
		if (entries != null) {
			entries = entries.stream().map(x -> {
				x.setCartEntryId(0);
				return x;
			}).collect(Collectors.toList());

			Session session = sf.openSession();
			session.beginTransaction();
			user = session.get(User.class, user.getUserEmail());
			UserDetails ud = user.getUserDetails();
			if (ud != null) {
				for (CartEntry entry : entries) {
					session.save(entry);
				}
				ud.getCart().getCartEntries().addAll(entries);
			}
			session.update(user);
			session.getTransaction().commit();
			session.close();
		}
		return entries;
	}

	@Override
	public void updateCart(User user, Cart cart) {
		cart.setCartId(0);
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		UserDetails ud = user.getUserDetails();
		if (ud != null) {
			Cart temp = ud.getCart();
			ud.setCart(cart);
			if (temp != null)
				session.evict(temp);
		}
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

}
