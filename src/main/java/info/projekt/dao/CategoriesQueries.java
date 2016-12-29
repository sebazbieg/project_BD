package info.projekt.dao;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Categories;

public class CategoriesQueries {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static ArrayList<Categories> categoriesList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Categories";
		Query<Categories> query = session.createQuery(hql);
		ArrayList<Categories> results = (ArrayList<Categories>) query.list();
		session.getTransaction().commit();
		session.close();
		// System.out.println("Rozmiar listy to: " + results.size());
		return results;
	}

	@SuppressWarnings("unchecked")
	public static Categories getCategory(String categoryName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Categories " + "WHERE categoryName = :categoryName";
		Query<Categories> query = session.createQuery(hql);
		query.setParameter("categoryName", categoryName);
		Categories result = (Categories) query.getSingleResult();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public static Categories getCategory(Byte categoryId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Categories " + "WHERE categoryId = :categoryId";
		Query<Categories> query = session.createQuery(hql);
		query.setParameter("categoryId", categoryId);
		Categories result = (Categories) query.getSingleResult();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}
}
