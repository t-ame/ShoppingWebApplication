package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.java.repository.CategoryRepositoryImpl;
import com.java.repository.ProductRepositoryImpl;

@Service("productservice")
public class ProductServiceImpl {

	@Autowired
	@Qualifier("productrep")
	ProductRepositoryImpl productRepository;

	@Autowired
	@Qualifier("categoryrep")
	CategoryRepositoryImpl categoryRepository;

}
