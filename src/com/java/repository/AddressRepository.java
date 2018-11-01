package com.java.repository;

import com.java.components.Address;
import com.java.components.User;

public interface AddressRepository {

	void updateAddress(Address address);

	Address getAddress(long id);

	void saveAddress(User user, Address address);
	
}
