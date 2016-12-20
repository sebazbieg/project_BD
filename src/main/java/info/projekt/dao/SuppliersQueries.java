package info.projekt.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Categories;
import info.projekt.database.Suppliers;

public class SuppliersQueries {

	public static ArrayList<Suppliers> suppliersList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Suppliers";
		Query query = session.createQuery(hql);
		ArrayList<Suppliers> results = (ArrayList<Suppliers>) query.list();
		session.getTransaction().commit();
		session.close();
		return results;
	}
	
	public static Suppliers getSuppliers(String companyName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Suppliers " + "WHERE companyName = :companyName";
		Query<Suppliers> query = session.createQuery(hql);
		query.setParameter("companyName", companyName);
		Suppliers result = (Suppliers) query.getSingleResult();
//		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
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
