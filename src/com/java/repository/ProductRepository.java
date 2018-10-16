package com.java.repository;

import java.util.List;
import com.java.components.ProductCategory;
import com.java.components.Product;

public interface ProductRepository {

	public Product getProduct(int id);

	public List<String> getProductNames(String substring);

	public List<Product> getProductsWithName(String substring);

	public List<Product> getProductsFromCategory(ProductCategory cat);

}
