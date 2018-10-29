package com.java.service;

import java.util.List;

import com.java.components.Product;
import com.java.components.ProductCategory;

public interface ProductService {


	public Product getProduct(long id);

//	public List<String> getProductNames(String substring);
	
	public void addProduct(Product product);

	public List<Product> getProductsWithName(String substring, int page);

	public List<Product> getProductsFromCategory(String catclass, int page);
	
	public List<Product> getProductsCategoryWithName(String catclass, String substring, int page);
	
	public boolean purchaseProduct(Product product, int quantity);
	
}
