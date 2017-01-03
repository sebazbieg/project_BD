package info.projekt.dao;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Products;

public class ProductsQueries {

	@SuppressWarnings({ "unchecked", "deprecation" })
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

	@SuppressWarnings("unchecked")
	public static Integer deleteProducts(Integer productId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Products " + "WHERE productId = :productId";
		Query<Products> query = session.createQuery(hql);
		query.setParameter("productId", productId);
		Integer result = null;
		result = query.executeUpdate();
//		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public static Products getProducts(Integer productId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Products " + "WHERE productId = :productId";
		Query<Products> query = session.createQuery(hql);
		query.setParameter("productId", productId);
		Products result = (Products) query.getSingleResult();
//		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public static Integer addProducts(Products products) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Products newProducts = new Products(products.getCategories(), products.getSuppliers(),
				products.getProductName(), products.getQuantityPerUnit(), products.getUnitPrice(),
				products.getUnitsInStock(), products.getUnitsOnOrder(), products.getReorderLevel(),
				products.getDiscontinued());
		session.beginTransaction();
		Integer result = (Integer) session.save(newProducts);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public static void updateProducts(Integer productId, short quantity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Products " + "WHERE productId = :productId";
		Query<Products> query = session.createQuery(hql);
		query.setParameter("productId", productId);
		Products result = (Products) query.getSingleResult();
		short tempQuantity = (short) (result.getUnitsInStock() - quantity);
		String hql2 = "update Products set unitsInStock = :tempQuantity where productId = :productId";
		Query<Products> query2 = session.createQuery(hql2);
		query2.setParameter("tempQuantity", tempQuantity);
		query2.setParameter("productId", productId);
		query2.executeUpdate();
		// System.out.println("Rows affected: " + result2);
		session.getTransaction().commit();
		session.close();
	}

}
