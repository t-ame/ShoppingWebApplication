package com.java.components;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "PRODUCT_TABLE")
@DynamicUpdate
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Product {

	private long productId;
	private String productName;
	private String imageUrl;
	private String catName;
	private float unitPrice;
	private int productRating;
	private int stockQuantity;
	private float productFrequency;
	private String productDescription;
	private Set<ProductDetailGroup> productDetails;

	@Column(name = "CATEGORY_NAME", nullable = false, length = 50)
	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name = "PRODUCT_DETAILS", joinColumns = { @JoinColumn(name = "PRODUCT_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PRODUCT_DETAIL_GROUP_ID") })
	public Set<ProductDetailGroup> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Set<ProductDetailGroup> productDetails) {
		this.productDetails = productDetails;
	}

	@Column(name = "PRODUCT_NAME", nullable = false, length = 100)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "PRODUCT_IMG_URL", nullable = false, length = 250)
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "UNIT_PRICE", nullable = false)
	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float basePrice) {
		this.unitPrice = basePrice;
	}

	@Column(name = "PRODUCT_RATING", nullable = false)
	public int getProductRating() {
		return productRating;
	}

	public void setProductRating(int productRating) {
		this.productRating = productRating;
	}

	@Column(name = "STOCK_QUANTITY", nullable = false)
	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int quantity) {
		this.stockQuantity = quantity;
	}

	@Column(name = "ORDER_FREQUENCY", nullable = false)
	public float getProductFrequency() {
		return productFrequency;
	}

	public void setProductFrequency(float productFrequency) {
		this.productFrequency = productFrequency;
	}

	@Column(name = "PRODUCT_DESCRIPTION", nullable = true, length = 500)
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Override
	public String toString() {
		return "[ " + productName 
				+ ", " + productDetails + " ]";
	}

}
