package com.java.service;

import com.java.components.Card;
import com.java.components.User;

public interface CardService {

	Card getCard(long id);

	void saveCard(User user, Card card);

	void updateCard(Card card);

}
