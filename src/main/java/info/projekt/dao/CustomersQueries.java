package info.projekt.dao;

import java.util.ArrayList;
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

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static ArrayList<Customers> customersList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Customers";
		Query<Customers> query = session.createQuery(hql);
		ArrayList<Customers> results = (ArrayList<Customers>) query.list();
		session.getTransaction().commit();
		session.close();
		// System.out.println("Rozmiar listy to: " + results.size());
		return results;

	}

	@SuppressWarnings("unchecked")
	public static void updateCompanyName(String companyName, String newCompanyName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query<Customers> query = session
				.createQuery("update Customers set companyName = :stockName" + " where companyName = :stockName2");
		query.setParameter("stockName", newCompanyName);
		query.setParameter("stockName2", companyName);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public static void deleteCustomers(String companyName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Customers " + "WHERE companyName = :company_name";
		Query<Customers> query = session.createQuery(hql);
		query.setParameter("company_name", companyName);
		query.executeUpdate();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public static Customers getCustomer(String companyName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Customers " + "WHERE companyName = :companyName";
		Query<Customers> query = session.createQuery(hql);
		query.setParameter("companyName", companyName);
		Customers result = (Customers) query.getSingleResult();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public static Customers getCustomerById(String customerId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Customers " + "WHERE customerId = :customerId";
		Query<Customers> query = session.createQuery(hql);
		query.setParameter("customerId", customerId);
		Customers result = (Customers) query.getSingleResult();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}
}