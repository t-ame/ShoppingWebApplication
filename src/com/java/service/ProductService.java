package com.java.service;

import java.util.List;

import com.java.components.Product;
import com.java.components.ProductCategory;

public interface ProductService {


	public Product getProduct(int id);

//	public List<String> getProductNames(String substring);

	public List<Product> getProductsWithName(String substring);

	public List<Product> getProductsFromCategory(Class<? extends Product> catclass);
	
	public List<Product> getProductsCategoryWithName(Class<? extends Product> catclass, String substring);
	
}
