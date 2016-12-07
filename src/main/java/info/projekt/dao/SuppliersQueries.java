package info.projekt.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Suppliers;

public class SuppliersQueries {

	public static Suppliers getSupplier() {
		int id = 1;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Suppliers where supplierId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Suppliers results = (Suppliers) query.getSingleResult();
		session.getTransaction().commit();
		session.close();
		return results;
	}
}
