package com.java.components;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "PRODUCT_DETAIL_GROUP_TABLE")
@DynamicUpdate
@Immutable
public class ProductDetailGroup {

	private int groupId;
	private String groupName;
	private Set<ProductDetail> detailValues;

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_DETAIL_GROUP_ID")
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Column(name = "DETAIL_VALUE", nullable = false, length = 20)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PRODUCT_DETAIL_GROUPING", joinColumns = { @JoinColumn(name = "PRODUCT_DETAIL_GROUP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PRODUCT_DETAIL_ID") })
	public Set<ProductDetail> getDetailValues() {
		return detailValues;
	}

	public void setDetailValues(Set<ProductDetail> detailValue) {
		this.detailValues = detailValue;
	}

	@Override
	public String toString() {
		return "[ " + groupName + " , " + detailValues + " ]";
	}

}
