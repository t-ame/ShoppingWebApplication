package com.java.components;

public class ProductDetail {

	private int detailId;
	private int productId;
	private int groupId;
	private String detailValue;
	private float priceIncrement;

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getDetailValue() {
		return detailValue;
	}

	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
	}

	public float getPriceIncrement() {
		return priceIncrement;
	}

	public void setPriceIncrement(float priceIncrement) {
		this.priceIncrement = priceIncrement;
	}

}
