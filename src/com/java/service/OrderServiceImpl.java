package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.components.Order;
import com.java.repository.OrderRepositoryImpl;

@Service("orderservice")
public class OrderServiceImpl implements OrderService{

	@Autowired
	@Qualifier("orderrep")
	OrderRepositoryImpl orderRep;

	@Override
	public void completeOrder(Order order) {
		orderRep.completeOrder(order);
	}
	
//	@Override
//	public void addOrders(List<Order> orders) {
//		orderRep.addOrders(orders);
//	}


//	@Override
//	public List<Order> getOrders() {
//		orderRep.getOrders();
//		return null;
//	}

	
	
}
