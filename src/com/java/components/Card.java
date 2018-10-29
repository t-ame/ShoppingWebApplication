package com.java.components;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;
import org.springframework.lang.NonNull;

import com.java.util.CcardDateConstraint;

@Entity
@Table(name = "CARD_TABLE")
@DynamicUpdate
@Immutable
public class Card {

	public enum CardType {
		DISCOVER, VISA, AMERICAN_EXPRESS
	}

	private long cardId;
	@NonNull
	@NotBlank
	private long cardNumber;
	@NonNull
	@NotBlank
	private int cvv;
	@NonNull
	@NotBlank
	private String cardName;
	
	@CcardDateConstraint
	private String expiryDate;
	private Address billingAddress;
	CardType cardType;

	@Id
	@GeneratedValue
	@Column(name = "CARD_ID")
	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	@Column(name = "CARD_NAME", nullable = false, length = 100)
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	@Column(name = "CARD_NO", nullable = false)
	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column(name = "EXP_DATE", nullable = false, length = 10)
	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Column(name = "CARD_CVV", nullable = false)
	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CARD_TYPE", nullable = false, length = 25)
	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

}
