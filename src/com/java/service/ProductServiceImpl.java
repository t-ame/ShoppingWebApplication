package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.components.Product;
import com.java.repository.ProductRepositoryImpl;
import com.java.util.ProductSorter;
import com.java.util.PurchaseStatus;

@Service("productservice")
public class ProductServiceImpl implements ProductService {

	@Autowired
	@Qualifier("productrep")
	ProductRepositoryImpl productRepository;

	ProductSorter psorter = new ProductSorter();

	@Override
	public Product getProduct(long id) {
		return productRepository.getProduct(id);
	}

	@Override
	public List<Product> getProductsWithName(String substring, int page) {
		List<Product> plist = productRepository.getProductsWithName(substring, page);
		psorter.setSearchkeys(substring);
		plist.sort(psorter);
		return plist;
	}

	@Override
	public List<Product> getProductsFromCategory(String catclass, int page) {
		List<Product> plist = productRepository.getProductsFromCategory(catclass, page);
		plist.sort(psorter);
		return plist;
	}

	@Override
	public List<Product> getProductsCategoryWithName(String catclass, String substring, int page) {
		List<Product> plist = productRepository.getProductsCategoryWithName(catclass, substring, page);
		psorter.setSearchkeys(substring);
		plist.sort(psorter);
		return plist;
	}

	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean purchaseProduct(Product product, int quantity) {
		return productRepository.purchaseProduct(product, quantity);
	}

	@Override
	public int getPageCount(String catclass, String substring) {
		return productRepository.getPageCount(catclass, substring);
	}

}
