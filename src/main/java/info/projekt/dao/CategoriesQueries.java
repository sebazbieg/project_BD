package info.projekt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Categories;
import info.projekt.database.Customers;

public class CategoriesQueries {

	public static Categories getCustomer() {
		int id = 1;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "from Categories where categoryId = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Categories results = (Categories) query.getSingleResult();
		System.out.println("bum10");
		session.getTransaction().commit();
		session.close();
		return results;
	}

	public static List<Categories> categoriesList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Categories";
		Query query = session.createQuery(hql);
		List<Categories> results = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("Rozmiar listy to: " + results.size());
		return results;

	}
}
