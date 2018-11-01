package com.java.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.components.Address;
import com.java.components.User;
import com.java.components.UserDetails;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

	@Autowired
	SessionFactory sf;

	@Override
	public void saveAddress(User user, Address address) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(address);
		user = session.get(User.class, user.getUserEmail());
		if (user != null) {
			UserDetails details = user.getUserDetails();
			if (details != null) {
				details.addAddress(address);
				session.update(details);
			}
		}
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateAddress(Address address) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(address);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Address getAddress(long id) {
		Session session = sf.openSession();
		Address address = session.get(Address.class, id);
		session.close();
		return address;
	}

}
