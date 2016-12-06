package info.projekt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Products;

public class ProductsQueries {

	public static List<Products> ProductsList() {

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

}
