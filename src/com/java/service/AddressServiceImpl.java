package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.components.Address;
import com.java.components.User;
import com.java.repository.AddressRepositoryImpl;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepositoryImpl addressRep;
	
	@Override
	public void saveAddress(User user, Address address) {
		addressRep.saveAddress(user, address);
	}

	@Override
	public void updateAddress(Address address) {
		addressRep.updateAddress(address);
	}

	@Override
	public Address getAddress(long id) {
		return addressRep.getAddress(id);
	}

}
