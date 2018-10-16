package com.java.repository;

import java.util.List;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import com.java.components.ProductCategory;

@Repository("categoryrep")
//@DependsOn("flyway")
public class CategoryRepositoryImpl implements CategoryRepository {

	@Override
	public List<ProductCategory> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

}
