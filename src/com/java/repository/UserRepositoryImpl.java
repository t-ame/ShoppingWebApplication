package com.java.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.components.Cart;
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
		
//		user = session.createQuery("select u from User u join fetch u.userDetails where u.userEmail='"+email+"'", User.class).uniqueResult();
		System.out.println("from get method "+user);

//		System.out.println(user);
		
		session.close();
		return user;
	}

	public void updateCart(User user, Cart cart) {
		Session session = sf.openSession();
		session.beginTransaction();
		user = session.get(User.class, user.getUserEmail());
		UserDetails ud = user.getUserDetails();
		if(ud != null) {
			if(ud.getCart() != null) {
				ud.getCart().addEntries(cart.getCartEntries());
			} else {
				ud.setCart(cart);
			}
		}
		session.update(user);
		session.getTransaction().commit();
		System.out.println("from update cart method "+user);
		session.close();
	}
	
	public void updateUser(User user) {
		Session session = sf.openSession();
		session.beginTransaction();
		System.out.println("update "+user);
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
		if(userD != null) {
			session.saveOrUpdate(userD);
			
		}
	}

	public void deleteUserDetails(UserDetails userD, Session session) {
		Cart cart = session.get(UserDetails.class, userD.getUserId()).getCart();
		if (cart != null) {
			session.delete(cart);
			;
		}
		session.delete(userD);
	}

}
