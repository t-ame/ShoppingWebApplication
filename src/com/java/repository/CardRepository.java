package com.java.repository;

import com.java.components.Card;
import com.java.components.User;

public interface CardRepository {

	Card getCard(long id);

	void saveCard(User user, Card card);

	void updateCard(Card card);

}
