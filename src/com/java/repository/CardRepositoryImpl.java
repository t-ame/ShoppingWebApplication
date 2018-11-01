package com.java.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.components.Card;
import com.java.components.User;
import com.java.components.UserDetails;

@Repository
public class CardRepositoryImpl implements CardRepository {

	@Autowired
	SessionFactory sf;

	@Override
	public Card getCard(long id) {
		Session session = sf.openSession();
		Card card = session.get(Card.class, id);
		session.close();
		return card;
	}

	@Override
	public void saveCard(User user, Card card) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(card);
		user = session.get(User.class, user.getUserEmail());
		if (user != null) {
			UserDetails details = user.getUserDetails();
			if (details != null) {
				details.addAddress(card.getBillingAddress());
				details.addCard(card);
				session.update(details);
			}
		}
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void updateCard(Card card) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(card);
		session.getTransaction().commit();
		session.close();
	}

}
