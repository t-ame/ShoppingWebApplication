package com.java.components;

import java.util.Map;
import java.util.Set;

public class Product {

	private int idproductId;
	private int categoryId;
	private String productName;
	private String imageUrl;
	private float basePrice;
	private int productRating;
	private int quantity;
	private float productFrequency;
	private String productDescription;
//	private Set<Integer> categoryIds;
//	private Map<String, Set<String>> Properties; // eg. color: red, green, brown... size: small, medium, large... etc.


	public int getIdproductId() {
		return idproductId;
	}

	public void setIdproductId(int idproductId) {
		this.idproductId = idproductId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public int getProductRating() {
		return productRating;
	}

	public void setProductRating(int productRating) {
		this.productRating = productRating;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getProductFrequency() {
		return productFrequency;
	}

	public void setProductFrequency(float productFrequency) {
		this.productFrequency = productFrequency;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
