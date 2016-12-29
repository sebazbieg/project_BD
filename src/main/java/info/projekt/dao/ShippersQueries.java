package info.projekt.dao;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Shippers;

public class ShippersQueries {

	@SuppressWarnings({ "unchecked", "deprecation" })
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

	@SuppressWarnings("unchecked")
	public static Shippers getShipper(String companyName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Shippers " + "WHERE companyName = :companyName";
		Query<Shippers> query = session.createQuery(hql);
		query.setParameter("companyName", companyName);
		Shippers result = (Shippers) query.getSingleResult();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public static Shippers getShipper(Integer shipperId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Shippers " + "WHERE shipperId = :shipperId";
		Query<Shippers> query = session.createQuery(hql);
		query.setParameter("shipperId", shipperId);
		Shippers result = (Shippers) query.getSingleResult();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

}
