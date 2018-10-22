package com.java.repository;

import java.util.List;
import com.java.components.ProductCategory;
import com.java.exception.MyCustomException;
import com.java.components.Product;

public interface ProductRepository {

	public Product getProduct(int id);

//	public List<String> getProductNames(String substring);
	
//	public void addProduct(Product product) throws MyCustomException;

	public List<Product> getProductsWithName(String substring);

	public List<Product> getProductsFromCategory(Class<? extends Product> catclass);

	public List<Product> getProductsCategoryWithName(Class<? extends Product> catclass, String substring);

}
