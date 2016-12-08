package info.projekt.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Orders;

public class OrdersQueries {
	public static ArrayList<Orders> OrdersList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Orders";
		Query<Orders> query = session.createQuery(hql);
		ArrayList<Orders> results = (ArrayList<Orders>) query.list();
		session.getTransaction().commit();
		session.close();
		// System.out.println("Rozmiar listy to: " + results.size());
		return results;
	}
}
