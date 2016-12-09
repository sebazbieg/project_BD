package info.projekt.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Employees;

public class EmployeesQueries {
	public static ArrayList<Employees> employeesList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Employees";
		Query query = session.createQuery(hql);
		ArrayList<Employees> results = (ArrayList<Employees>) query.list();
		session.getTransaction().commit();
		session.close();
		// System.out.println("Rozmiar listy to: " + results.size());
		return results;
	}

}
