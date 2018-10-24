package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.components.Product;
import com.java.components.ProductCategory;
import com.java.repository.ProductRepositoryImpl;
import com.java.util.ProductSorter;

@Service("productservice")
public class ProductServiceImpl implements ProductService{

	@Autowired
	@Qualifier("productrep")
	ProductRepositoryImpl productRepository;
	
	ProductSorter psorter = new ProductSorter();

	@Override
	public Product getProduct(long id) {
		return productRepository.getProduct(id);
	}

	@Override
	public List<Product> getProductsWithName(String substring) {
		List<Product> plist = productRepository.getProductsWithName(substring);
		psorter.setSearchkeys(substring);
		plist.sort(psorter);
		return plist;
	}

	@Override
	public List<Product> getProductsFromCategory(Class<? extends Product> catclass) {
		List<Product> plist = productRepository.getProductsFromCategory(catclass);
		plist.sort(psorter);
		return plist;
	}

	@Override
	public List<Product> getProductsCategoryWithName(Class<? extends Product> catclass, String substring) {
		List<Product> plist = productRepository.getProductsFromCategory(catclass);
		psorter.setSearchkeys(substring);
		plist.sort(psorter);
		return plist;
	}

}
