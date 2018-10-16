package com.java.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import com.java.components.ProductCategory;
import com.java.components.Product;

@Repository("productrep")
//@DependsOn("flyway")
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	BasicDataSource ds;
	
	@Override
	public Product getProduct(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getProductNames(String substring) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsWithName(String substring) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsFromCategory(ProductCategory cat) {
		// TODO Auto-generated method stub
		return null;
	}

}
