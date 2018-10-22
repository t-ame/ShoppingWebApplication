package com.java.components;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "CART_ENTRY_TABLE")
@DynamicUpdate
public class CartEntry {

	private Product product;
	private long cartEntryId;
	private int quantity;
	private List<ProductDetail> cartEntryDetails;

	@Id
	@GeneratedValue
	@Column(name = "CART_ENTRY_ID")
	public long getCartEntryId() {
		return cartEntryId;
	}

	public void setCartEntryId(long cartEntryId) {
		this.cartEntryId = cartEntryId;
	}

	@OneToOne
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "ENTRY_QUANTITY", nullable = false)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CART_ENTRY_DETAILS", joinColumns = {
			@JoinColumn(name = "CART_ENTRY_ID") }, inverseJoinColumns = { @JoinColumn(name = "PRODUCT_DETAIL_ID") })
	public List<ProductDetail> getCartEntryDetails() {
		return cartEntryDetails;
	}

	public void setCartEntryDetails(List<ProductDetail> cartEntryDetails) {
		this.cartEntryDetails = cartEntryDetails;
	}

}
