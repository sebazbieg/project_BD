package info.projekt.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Shippers;

public class ShippersQueries {
	
	public static ArrayList<Shippers> shippersList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Shippers";
		Query<Shippers> query = session.createQuery(hql);
		ArrayList<Shippers> results = (ArrayList<Shippers>) query.list();
		session.getTransaction().commit();
		session.close();
		// System.out.println("Rozmiar listy to: " + results.size());
		return results;
	}

}
