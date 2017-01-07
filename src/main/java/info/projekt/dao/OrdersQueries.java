package info.projekt.dao;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Orders;

public class OrdersQueries {
	@SuppressWarnings({ "unchecked", "deprecation" })
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

	@SuppressWarnings("unchecked")
	public static void deleteOrders(Integer orderId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Orders " + "WHERE orderId = :orderId";
		Query<Orders> query = session.createQuery(hql);
		query.setParameter("orderId", orderId);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public static void deleteOrdersByFreight(Double freight) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Orders " + "WHERE freight = :freight";
		Query<Orders> query = session.createQuery(hql);
		query.setParameter("freight", freight);
		query.executeUpdate();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
	}

	public static Integer addOrders(Orders orders) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Orders newOrders = new Orders(orders.getCustomers(), orders.getEmployees(), orders.getShippers(),
				orders.getOrderDate(), null, null, orders.getFreight(), orders.getShipName(), orders.getShipAddress(),
				orders.getShipCity(), orders.getShipRegion(), orders.getShipPostalCode(), orders.getShipCountry(),
				orders.getOrderDetailses(), null);
		session.beginTransaction();
		Integer result = (Integer) session.save(newOrders);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public static Orders getOrder(Integer orderId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM Orders " + "WHERE orderId = :orderId";
		Query<Orders> query = session.createQuery(hql);
		query.setParameter("orderId", orderId);
		Orders result = (Orders) query.getSingleResult();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

}
