package info.projekt.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Employees;

public class EmployeesQueries {

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static ArrayList<Employees> employeesList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Employees";
		Query<Employees> query = session.createQuery(hql);
		ArrayList<Employees> results = (ArrayList<Employees>) query.list();
		session.getTransaction().commit();
		session.close();
		// System.out.println("Rozmiar listy to: " + results.size());
		return results;
	}

	@SuppressWarnings("unchecked")
	public static Employees getEmployee(String firstName, String lastName, String title) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Employees " + "WHERE firstName = :firstName and lastName = :lastName and title = :title";
		Query<Employees> query = session.createQuery(hql);
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		query.setParameter("title", title);
		Employees result = (Employees) query.getSingleResult();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public static Employees getEmployee(Integer employeeId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Employees " + "WHERE employeeId = :employeeId";
		Query<Employees> query = session.createQuery(hql);
		query.setParameter("employeeId", employeeId);
		Employees result = (Employees) query.getSingleResult();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

}
