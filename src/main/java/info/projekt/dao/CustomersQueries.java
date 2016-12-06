package info.projekt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Customers;
import info.projekt.dao.HibernateUtil;

public class CustomersQueries {

	public static void addCustomers(Customers customers) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Customers newCustomers = new Customers(customers.getCustomerId(), customers.getCompanyName(),
				customers.getContactName(), customers.getContactTitle(), customers.getAddress(), customers.getCity(),
				customers.getRegion(), customers.getPostalCode(), customers.getCountry(), customers.getPhone(),
				customers.getFax());
		session.beginTransaction();
		session.save(newCustomers);
		session.getTransaction().commit();
		session.close();
	}

	public static List<Customers> customersList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Customers";
		Query query = session.createQuery(hql);
		List<Customers> results = query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("Rozmiar listy to: " + results.size());
		return results;

	}

	public static void updateCompanyName(String companyName, String newCompanyName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session
				.createQuery("update Customers set companyName = :stockName" + " where companyName = :stockName2");
		query.setParameter("stockName", newCompanyName);
		query.setParameter("stockName2", companyName);
		int result = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	public static void deleteCustomers(String companyName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Customers " + "WHERE companyName = :company_name";
		Query query = session.createQuery(hql);
		query.setParameter("company_name", companyName);
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
	}
}