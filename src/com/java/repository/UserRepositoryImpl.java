package com.java.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.components.Address;
import com.java.components.Card;
import com.java.components.Cart;
import com.java.components.CartEntry;
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
		User user = new User();
		Session session = sf.openSession();
		user = session.get(User.class, email);
		session.close();
		return user;
	}

	@Override
	public Set<Card> getCards(User user) {
		Session session = sf.openSession();
		user = session.get(User.class, user.getUserEmail());
		UserDetails details = user.getUserDetails();
		Set<Card> cards = null;
		if (details != null) {
			cards = details.getCards();
		} else {
			cards = new HashSet<>();
		}
		session.close();
		if (cards == null)
			cards = new HashSet<>();
		return cards;
	}

	public void updateAddress(User user, Address address) {
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		UserDetails ud = user.getUserDetails();
		if (ud != null) {
			ud.addAddress(address);
		}
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	public void updateUser(User user) {
		Session session = sf.openSession();
		Set<Address> addresses = user.getUserDetails().getAddresses();

		session.beginTransaction();

		session.get(User.class, user.getUserEmail()).getUserDetails().addAddress(addresses.iterator().next());

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
		if (userD != null) {
			session.saveOrUpdate(userD);

		}
	}

	public void deleteUserDetails(UserDetails userD, Session session) {
		Cart cart = session.get(UserDetails.class, userD.getUserId()).getCart();
		if (cart != null) {
			session.delete(cart);
		}
		session.delete(userD);
	}

	// TO BE IMPLEMENTED
	public void updateUser(User user, Cart cart, Address address, List<Order> orders) {
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		UserDetails ud = user.getUserDetails();
		if (ud != null) {
			Cart temp = ud.getCart();
			ud.setCart(cart);
			session.delete(temp);
		}
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	// TO BE TESTED
	public void updateUser(User user, Address address) {
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		UserDetails ud = user.getUserDetails();
		if (ud != null && address.getAddressId() == 0) {
			session.save(address);
			ud.addAddress(address);
		}
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	// TO BE TESTED
	public void updateOrders(User user, List<Order> orders) {
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		UserDetails ud = user.getUserDetails();
		if (ud != null) {
			Cart temp = ud.getCart();
			ud.setCart(new Cart());
			ud.addOrders(orders);
			if (temp != null)
				session.delete(temp);
		}
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Set<Address> getAddresses(User user) {
		Session session = sf.openSession();
		user = session.get(User.class, user.getUserEmail());
		UserDetails details = user.getUserDetails();
		Set<Address> addresses = null;
		if (details != null) {
			addresses = details.getAddresses();
		} else {
			addresses = new HashSet<>();
		}
		session.close();
		if (addresses == null)
			addresses = new HashSet<>();
		return addresses;
	}

}
