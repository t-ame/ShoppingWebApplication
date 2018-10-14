package com.java.components;

import java.util.Set;

public class CartEntry {

	private String productName;
	private int productId;
	private int cartId;
	private int quantity;
	private float price;
	private Set<String> productPopertyDetails;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Set<String> getProductPopertyDetails() {
		return productPopertyDetails;
	}

	public void setProductPopertyDetails(Set<String> productPopertyDetails) {
		this.productPopertyDetails = productPopertyDetails;
	}

}
