package info.projekt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Categories;
import info.projekt.database.Customers;
import info.projekt.database.OrderDetails;
import info.projekt.database.Products;
import info.projekt.database.Suppliers;

public class ProductsQueries {

	public static ArrayList<Products> ProductsList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Products";
		Query<Products> query = session.createQuery(hql);
		ArrayList<Products> results = (ArrayList<Products>) query.list();
		session.getTransaction().commit();
		session.close();
		// System.out.println("Rozmiar listy to: " + results.size());
		return results;
	}

	public static void deleteProducts(Integer productId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Products " + "WHERE Integer productId = :productId";
		Query<Products> query = session.createQuery(hql);
		query.setParameter("productId", productId);
		int result = query.executeUpdate();
//		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
	}

	public static void addProducts(Products products) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Products newProducts = new Products(products.getCategories(), products.getSuppliers(),
				products.getProductName(), products.getQuantityPerUnit(), products.getUnitPrice(),
				products.getUnitsInStock(), products.getUnitsOnOrder(), products.getReorderLevel(),
				products.getDiscontinued());
		session.beginTransaction();
		session.save(newProducts);
		session.getTransaction().commit();
		session.close();
	}

}
