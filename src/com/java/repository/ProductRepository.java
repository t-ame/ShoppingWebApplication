package com.java.repository;

import java.util.List;
import com.java.components.ProductCategory;
import com.java.exception.MyCustomException;
import com.java.components.Product;

public interface ProductRepository {

	public Product getProduct(long id);

//	public List<String> getProductNames(String substring);
	
//	public void addProduct(Product product) throws MyCustomException;
	
	public void addProduct(Product product);

	public List<Product> getProductsWithName(String substring, int page);

	public List<Product> getProductsFromCategory(String catclass, int page);

	public List<Product> getProductsCategoryWithName(String catclass, String substring, int page);

	int getPageCount(String catclass, String substring);

	boolean purchaseProduct(Product product, int quantity);

}
