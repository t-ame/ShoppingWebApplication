package com.java.components;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "CART_TABLE")
@DynamicUpdate
public class Cart {

	private long cartId;
	private List<CartEntry> cartEntries;

	public Cart() {
		super();
		cartEntries = new ArrayList<>();
	}

	@Id
	@GeneratedValue
	@Column(name = "CART_ID")
	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "CART_ENTRIES", joinColumns = { @JoinColumn(name = "CART_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "CART_ENTRY_ID") })
	public List<CartEntry> getCartEntries() {
		return cartEntries;
	}

	public void setCartEntries(List<CartEntry> cartEntries) {
		this.cartEntries = cartEntries;
	}

	public void addToCart(CartEntry entry) {
		cartEntries.add(entry);
	}

	public void addEntries(List<CartEntry> entries) {
		cartEntries.addAll(entries);
	}

	public void removeFromCart(CartEntry entry) {
		cartEntries.remove(entry);
	}

	public void decreaseEntry(CartEntry entry, int decrement) {
		cartEntries.remove(entry);
		int nqty = entry.getQuantity() - decrement;
		if (nqty <= 0)
			removeFromCart(entry);
		else
			entry.setQuantity(nqty);
		cartEntries.add(entry);
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", cartEntries=" + cartEntries + "]";
	}

}
