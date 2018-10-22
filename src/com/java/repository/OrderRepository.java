package com.java.repository;

import java.util.List;

import org.hibernate.Session;

import com.java.components.Order;

public interface OrderRepository {

	public void addOrders(List<Order> orders, Session session);
	
//	public List<Order> getOrders();
	
	public void completeOrder(Order order);
}
