package com.java.components;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "PRODUCT_DETAIL_TABLE")
@DynamicUpdate
@Immutable
public class ProductDetail {

	private long detailId;
	private String detailValue;
	

	public ProductDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductDetail(String detailValue) {
		super();
		this.detailValue = detailValue;
	}

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_DETAIL_ID")
	public long getDetailId() {
		return detailId;
	}

	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}

	@Column(name = "DETAIL_VALUE", nullable = false, length = 50)
	public String getDetailValue() {
		return detailValue;
	}

	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
	}

	@Override
	public String toString() {
		return "[" + detailValue + "]";
	}

}
