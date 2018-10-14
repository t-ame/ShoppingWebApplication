package com.java.components;

import java.util.Map;
import java.util.Set;

public class Product {

	private String name;
	private String imageUrl;
	private Map<String, Set<String>> Properties;
	private float price;
	private int rating;
	private int quantity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Map<String, Set<String>> getProperties() {
		return Properties;
	}

	public void setProperties(Map<String, Set<String>> properties) {
		Properties = properties;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
