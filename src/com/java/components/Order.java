package com.java.components;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "ORDER_TABLE")
@DynamicUpdate
@Immutable
public class Order {

	private long orderId;
	private int quantity;
	private Product product;
	private Date orderDate;
	private boolean complete;
	private List<ProductDetail> productDetails;
	private String stringDetails="";

//	private Card card;
//	private Address shippingAddress;

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	@Column(name = "ORDER_QUANTITY", nullable = false)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

//	@OneToOne(orphanRemoval = false)
//	public Card getCard() {
//		return card;
//	}
//
//	public void setCard(Card card) {
//		this.card = card;
//	}

	@OneToOne(orphanRemoval = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

//	@OneToOne(orphanRemoval = false)
//	public Address getShippingAddress() {
//		return shippingAddress;
//	}
//
//	public void setShippingAddress(Address shippingAddress) {
//		this.shippingAddress = shippingAddress;
//	}

	@Column(name = "ORDER_DATE", nullable = false)
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "IS_COMPLETE", nullable = false, length = 10)
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	@OneToMany(orphanRemoval = false)
	@JoinTable(name = "ORDER_PRODUCT_DETAIL", joinColumns = { @JoinColumn(name = "ORDER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PRODUCT_DETAIL_ID") })
	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
		for(int i=0; i<productDetails.size(); ++i) {
			this.stringDetails += productDetails.get(i).toString()+", ";
		}
	}
	
	public void setStringDetails(String string) {
		this.stringDetails = string;
	}

	public String getStringDetails() {
		return stringDetails;
	}

}
