package com.java.repository;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.components.Address;
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
		User user = new User();
		Session session = sf.openSession();
		user = session.get(User.class, email);
		System.out.println("from get method " + user);
		session.close();
		return user;
	}

	public void updateCart(User user, Cart cart) {
		cart.setCartId(0);
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		UserDetails ud = user.getUserDetails();
		if (ud != null) {
			Cart temp = ud.getCart();
			ud.setCart(cart);
//			session.delete(temp);
			if(temp != null)
				session.evict(temp);
		}
		session.update(user);
		session.getTransaction().commit();
		System.out.println("from update cart method " + user);
		session.close();
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
		System.out.println("from update cart method " + user);
		session.close();
	}

	public void updateUser(User user) {
		Session session = sf.openSession();
		Set<Address> addresses = user.getUserDetails().getAddresses();
		
		session.beginTransaction();
//		System.out.println("update " + user);
		
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

	//TO BE IMPLEMENTED
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
		System.out.println("from update cart method " + user);
		session.close();
	}

	//TO BE TESTED
	public void updateUser(User user, Address address) {
		Session session = sf.openSession();
		session.beginTransaction();
		User us = session.get(User.class, user.getUserEmail());
		UserDetails ud = us.getUserDetails();
		if (ud != null) {
			ud.addAddress(address);
			user.getUserDetails().setAddresses(ud.getAddresses());
		}
		session.saveOrUpdate(user);
		session.update(user);
		session.getTransaction().commit();
		System.out.println("from update cart method " + user);
		session.close();
	}

	//TO BE TESTED
	public void updateOrders(User user, List<Order> orders) {
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		UserDetails ud = user.getUserDetails();
		if (ud != null) {
			Cart temp = ud.getCart();
			ud.setCart(new Cart());
			ud.addOrders(orders);
			if(temp != null)
				session.delete(temp);
		}
		session.update(user);
		session.getTransaction().commit();
		System.out.println("from update cart method " + user);
		session.close();
	}


}
