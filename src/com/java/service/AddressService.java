package com.java.service;

import com.java.components.Address;
import com.java.components.User;

public interface AddressService {

	public void saveAddress(User user,Address address);

	void updateAddress(Address address);

	Address getAddress(long id);
	
}
