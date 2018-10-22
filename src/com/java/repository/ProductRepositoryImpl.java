package com.java.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java.components.Product;
import com.java.components.ProductToString;
import com.java.exception.MyCustomException;

@Repository("productrep")
//@DependsOn("flyway")
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	SessionFactory sf;

	@Override
	public Product getProduct(int id) {
		Session session = sf.openSession();
		Product p = session.get(Product.class, id);
		session.close();
		return p;
	}

//	@Override
//	public List<String> getProductNames(String substring) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

	@Override
	public List<Product> getProductsWithName(String substring) {
		List<Product> products = new ArrayList<>();
		String qparam = " description is like %";
		String[] searchKeys = substring.split(" ");

		for (int i = 0; i < searchKeys.length; ++i) {
			qparam += searchKeys[i] + "% or description is like %";
		}
		
		Session session = sf.openSession();
		Query<ProductToString> query = session.createQuery("from ProductToString where" + qparam);
		List<ProductToString> productDesc = query.list();
		
		for(int i=0; i<productDesc.size(); ++i) {
			products.add(productDesc.get(i).getProduct());
		}
		session.close();
		return products;
	}

	@Override
	public List<Product> getProductsFromCategory(Class<? extends Product> catclass) {
		List<Product> products = new ArrayList<>();
		
		Session session = sf.openSession();
		Query<Product> query = session.createQuery("from "+catclass.getSimpleName());
		products = query.list();
		session.close();
		return products;
	}

	@Override
	public List<Product> getProductsCategoryWithName(Class<? extends Product> catclass, String substring) {
		List<Product> products = new ArrayList<>();
		String qparam = " description is like %";
		String[] searchKeys = substring.split(" ");

		for (int i = 0; i < searchKeys.length; ++i) {
			qparam += searchKeys[i] + "% or description is like %";
		}
		
		qparam += " and catName="+catclass.getSimpleName();
		
		Session session = sf.openSession();
		Query<ProductToString> query = session.createQuery("from ProductToString where" + qparam);
		List<ProductToString> productDesc = query.list();
		
		for(int i=0; i<productDesc.size(); ++i) {
			products.add(productDesc.get(i).getProduct());
		}
		session.close();
		return products;
	}

}
