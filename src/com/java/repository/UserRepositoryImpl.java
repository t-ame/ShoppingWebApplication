package com.java.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.components.Address;
import com.java.components.Card;
import com.java.components.Cart;
import com.java.components.Order;
import com.java.components.User;
import com.java.components.UserDetails;

@Repository("userrep")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	SessionFactory sf;

	@Autowired
	OrderRepositoryImpl orderRep;

	public void addUser(User user) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(user.getUserDetails());
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	public User getUser(String email) {
		User st = new User();
		Session session = sf.openSession();
		st = session.get(User.class, email);
		UserDetails ud = st.getUserDetails();
		ud.getCart();
		session.close();
		return st;
	}

	public void updateUser(User user) {
		Session session = sf.openSession();
		session.beginTransaction();
		updateUserDetails(user.getUserDetails(), session);
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	public void deleteUser(User user) {
		Session session = sf.openSession();
		session.beginTransaction();
		deleteUserDetails(user.getUserDetails(), session);
		session.delete(user);
		session.getTransaction().commit();
		session.close();
	}

	public void addUserDetails(UserDetails userD, Session session) {
		if (userD.getCart() != null)
			session.save(userD.getCart());
		session.save(userD);
	}

	public UserDetails getUserDetails(long id) {
		UserDetails st = new UserDetails();
		Session session = sf.openSession();
		st = session.get(UserDetails.class, id);
		session.close();
		return st;
	}

	public void updateUserDetails(UserDetails userD, Session session) {
		Cart cart = userD.getCart();
		List<Order> orders = userD.getOrders();
		List<Address> addresses = userD.getAddresses();
		List<Card> cards = userD.getCards();
		if (cart != null) {
			if (session.get(UserDetails.class, userD.getUserId()).getCart() != null) {
				session.update(cart);
			} else {
				session.save(cart);
			}
		}
		if (orders != null) {
			List<Order> oldOrders = session.get(UserDetails.class, userD.getUserId()).getOrders();
			if (oldOrders != null) {
				oldOrders.addAll(orders);
			}
			for(int i=0; i<orders.size(); ++i)
				session.save(orders.get(i));
			userD.setOrders(oldOrders);
		}
		if (addresses != null) {
			List<Address> oldAddresses = session.get(UserDetails.class, userD.getUserId()).getAddresses();
			if (oldAddresses != null) {
				oldAddresses.addAll(addresses);
			}
			for(int i=0; i<addresses.size(); ++i)
				session.save(addresses.get(i));
			userD.setAddresses(oldAddresses);
		}
		if (cards != null) {
			List<Card> oldCards = session.get(UserDetails.class, userD.getUserId()).getCards();
			if (oldCards != null) {
				oldCards.addAll(cards);
			}
			for(int i=0; i<cards.size(); ++i)
				session.save(cards.get(i));
			userD.setCards(oldCards);;
		}
		session.update(userD);
	}

	public void deleteUserDetails(UserDetails userD, Session session) {
		Cart cart = session.get(UserDetails.class, userD.getUserId()).getCart();
		if (cart != null) {
			session.delete(cart);;
		}
		session.delete(userD);
	}

}
