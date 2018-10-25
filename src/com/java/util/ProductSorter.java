package com.java.util;

import java.util.Comparator;

import com.java.components.Product;

public class ProductSorter implements Comparator<Product> {

	private String searchkeys;

	@Override
	public int compare(Product o1, Product o2) {
		int first = 0, second = 0;
		int rate1 = o1.getProductRating(), rate2 = o2.getProductRating();
		
		if (searchkeys != null) {
			String[] keys = searchkeys.toLowerCase().split(" ");

			String desc1 = o1.toString();
			String desc2 = o2.toString();

			for (int i = 0; i < keys.length; ++i) {
				if (desc1.contains(keys[i])) {
					++first;
				}
				if (desc2.contains(keys[i])) {
					++second;
				}
			}
		}

		if (first < second) {
			return -1;
		} else if (first > second) {
			return 1;
		} else {
			if (rate1 < rate2) {
				return -1;
			} else if (rate1 > rate2) {
				return 1;
			}
		}

		return 0;
	}

	public String getSearchkeys() {
		return searchkeys;
	}

	public void setSearchkeys(String searchkeys) {
		this.searchkeys = searchkeys;
	}

}
