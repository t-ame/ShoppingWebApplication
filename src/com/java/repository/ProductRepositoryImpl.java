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
import com.java.util.PurchaseStatus;

@Repository("productrep")
//@DependsOn("flyway")
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	SessionFactory sf;

	private static Object productLock = new Object();

	@Override
	public Product getProduct(long id) {
		Session session = sf.openSession();
		System.out.println(id);
		Product p = session.get(Product.class, id);
		System.out.println(p);
		session.close();
		return p;
	}

	@Override
	public void addProduct(Product product) {
		Session session = sf.openSession();
		String cname = product.getClass().getSimpleName();

		Product p = (Product) product;
		ProductToString pt = new ProductToString();
		pt.setProduct(product);
		session.beginTransaction();
		session.saveOrUpdate(pt);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Product> getProductsWithName(String substring, int page) {
		List<Product> products = new ArrayList<>();
		String qparam = "where";
		if (substring == null)
			substring = "";
		if (substring.equals("")) {
			qparam = "";
		} else {
			String[] searchKeys = substring.split(" ");

			for (int i = 0; i < searchKeys.length; ++i) {
				qparam += " description like '%" + searchKeys[i] + "%'";
				if (i < searchKeys.length - 1)
					qparam += " or";
			}
		}

		Session session = sf.openSession();

		Query<ProductToString> query = session.createQuery("from ProductToString " + qparam);

//		if (page == 0)
//			page = 1;
//
//		query.setFirstResult(9 * (page - 1));
//		query.setMaxResults(9);

		List<ProductToString> productDesc = query.list();
		session.close();

		for (int i = 0; i < productDesc.size(); ++i) {
			products.add(productDesc.get(i).getProduct());
		}
		return products;
	}

	@Override
	public List<Product> getProductsFromCategory(String catclass, int page) {

		List<Product> products = new ArrayList<>();

		Session session = sf.openSession();

		Query<ProductToString> query = session.createQuery("from ProductToString where catName='" + catclass + "'");

//		if (page == 0)
//			page = 1;
//
//		query.setFirstResult(9 * (page - 1));
//		query.setMaxResults(9);

		List<ProductToString> productDesc = query.list();

		session.close();

		for (int i = 0; i < productDesc.size(); ++i) {
			products.add(productDesc.get(i).getProduct());
		}
		return products;
	}

	@Override
	public List<Product> getProductsCategoryWithName(String catclass, String substring, int page) {

		List<Product> products = new ArrayList<>();
		String qparam = "where";
		if (substring == null)
			substring = "";
		if (!substring.equals("")) {
			String[] searchKeys = substring.split(" ");
			qparam += "(";
			for (int i = 0; i < searchKeys.length; ++i) {
				qparam += " description like '%" + searchKeys[i] + "%'";
				if (i < searchKeys.length - 1)
					qparam += " or";
			}
			qparam += ")";
		}
		if (!qparam.equals("where") && !qparam.equals(""))
			qparam += " and ";
		qparam += " catName='" + catclass + "'";

		Session session = sf.openSession();
		Query<ProductToString> query = session.createQuery("from ProductToString " + qparam);

//		if (page == 0)
//			page = 1;
//
//		query.setFirstResult(9 * (page - 1));
//		query.setMaxResults(9);

		List<ProductToString> productDesc = query.list();
		session.close();

		for (int i = 0; i < productDesc.size(); ++i) {
			products.add(productDesc.get(i).getProduct());
		}
		return products;
	}

	public boolean purchaseProduct(Product product, int quantity) {

		boolean status = false;
		Session session = sf.openSession();

		session.beginTransaction();
		synchronized(productLock) {
			Product p =session.get(Product.class, product.getProductId());
			int stock = p.getStockQuantity();
			if(stock >= quantity) {
				p.setStockQuantity(stock - quantity);
				status = true;
			}
			session.getTransaction().commit();
		}
		session.close();
		return status;
	}

}
