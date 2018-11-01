package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.components.Card;
import com.java.components.User;
import com.java.repository.CardRepositoryImpl;

@Service("cartservice")
public class CardServiceImpl implements CardService {

	@Autowired
	CardRepositoryImpl cardRepository;

	@Override
	public Card getCard(long id) {
		return cardRepository.getCard(id);
	}

	@Override
	public void saveCard(User user, Card card) {
		cardRepository.saveCard(user, card);
	}

	@Override
	public void updateCard(Card card) {
		cardRepository.updateCard(card);
	}

}
