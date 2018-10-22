package com.java.components;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "LOGIN_TABLE")
@DynamicUpdate
public class User {

	public enum Role {
		ADMIN, CUSTOMER
	}

	@Id
	@Column(name = "USER_EMAIL", nullable = false, length = 50)
	private String userEmail;

	@Column(name = "PASSWORD", nullable = false, length = 25)
	private String userPassword;

	@Enumerated(EnumType.STRING)
	@Column(name = "USER_ROLE", nullable = false, length = 20)
	private Role role;

	@OneToOne
	@JoinColumn(name = "USER_DETAILS")
	private UserDetails userDetails;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails user) {
		this.userDetails = user;
	}

}
