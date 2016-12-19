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
	
	public static Suppliers getSuppliers(Integer supplierId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Suppliers " + "WHERE supplierId = :supplierId";
		Query<Suppliers> query = session.createQuery(hql);
		query.setParameter("supplierId", supplierId);
		Suppliers result = (Suppliers) query.getSingleResult();
//		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}
}
