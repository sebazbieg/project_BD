package info.projekt.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Products;

public class RaportsQueries {
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static ArrayList<Object[]> customersList(Products products) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "SELECT c.companyName, sum(od.quantity) FROM Orders o JOIN o.orderDetailses od JOIN o.customers c WHERE od.products= :products GROUP BY c ORDER BY sum(od.quantity) desc";
		Query<Object[]> query = session.createQuery(hql);
		query.setParameter("products", products);
		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		session.getTransaction().commit();
		session.close();
		return results;
	}

}
