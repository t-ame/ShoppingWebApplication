package com.java.util;

import java.util.Comparator;

import com.java.components.Order;

public class OrderSorter implements Comparator<Order>{

	@Override
	public int compare(Order o1, Order o2) {
		if(!o1.isComplete() && o2.isComplete()) {
			return -1;
		} else if(o1.isComplete() && !o2.isComplete()) {
			return 1;
		} else {
			if(o1.getOrderDate().before(o2.getOrderDate())) {
				return -1;
			} else if (o1.getOrderDate().after(o2.getOrderDate())) {
				return 1;
			}
		}
		return 0;
	}

}
