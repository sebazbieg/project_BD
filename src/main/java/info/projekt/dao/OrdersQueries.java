package info.projekt.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Orders;
import info.projekt.database.Products;

public class OrdersQueries {
	public static ArrayList<Orders> OrdersList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Orders";
		Query<Orders> query = session.createQuery(hql);
		ArrayList<Orders> results = (ArrayList<Orders>) query.list();
		session.getTransaction().commit();
		session.close();
		// System.out.println("Rozmiar listy to: " + results.size());
		return results;
	}

	public static void deleteOrders(Integer ordersId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Orders " + "WHERE ordersId = :ordersId";
		Query<Products> query = session.createQuery(hql);
		query.setParameter("ordersId", ordersId);
		int result = query.executeUpdate();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
	}

	public static void addOrders(Orders orders) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Orders newOrders = new Orders(orders.getCustomers(), orders.getEmployees(), orders.getShippers(),
				orders.getOrderDate(), orders.getFreight(), orders.getShipName(), orders.getShipAddress(),
				orders.getShipCity(), orders.getShipRegion(), orders.getShipPostalCode(), orders.getShipCountry());
		session.beginTransaction();
		session.save(newOrders);
		session.getTransaction().commit();
		session.close();
	}

}
