package com.java.components;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "PRODUCT_TO_STRING")
@DynamicUpdate
@Immutable
public class ProductToString {

	@Id
	private long descriptionId;
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Product product;
	private String catName;

	@Column(name = "CATEGORY_NAME", nullable = false, length = 50)
	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	@Column(name = "DESCRIPTION_ID", nullable = false)
	public long getDescriptionId() {
		return descriptionId;
	}

	public void setDescriptionId(long descriptionId) {
		this.descriptionId = descriptionId;
	}

	@Column(name = "DESCRIPTION", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		setDescription(product.toString());
		setCatName(product.getCatName());
	}

}
