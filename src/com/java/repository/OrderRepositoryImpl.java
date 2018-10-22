package com.java.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.components.Order;
import com.java.components.ProductDetail;

@Repository("orderrep")
public class OrderRepositoryImpl implements OrderRepository{

	@Autowired
	SessionFactory sf;
	
	
	@Override
	public void addOrders(List<Order> orders, Session session) {
		for(int i=0; i< orders.size(); ++i) {
//			List<ProductDetail> details = orders.get(i).getProductDetails();
//			for(int j=0; j<details.size(); ++j) {
//				session.save(details.get(j));
//			}
			session.save(orders.get(i));
		}
	}

	@Override
	public void completeOrder(Order order) {
		Session s = sf.openSession();
		order = s.get(Order.class, order.getOrderId());
		order.setComplete(true);
		s.update(order);
		s.close();
	}

}
