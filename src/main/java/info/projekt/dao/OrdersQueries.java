package info.projekt.dao;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.Orders;

public class OrdersQueries {
	public static ArrayList<Orders> OrdersList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM Orders";
		Query<Orders> query = session.createQuery(hql);
		ArrayList<Orders> results = (ArrayList<Orders>) query.list();
		session.getTransaction().commit();
		session.close();
//		System.out.println("Rozmiar listy to: " + results.size());
		return results;
	}

	public static void deleteOrders(Integer ordersId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "DELETE FROM Orders " + "WHERE ordersId = :ordersId";
		Query query = session.createQuery(hql);
		query.setParameter("ordersId", ordersId);
		int result = query.executeUpdate();
		// System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
	}

	public static Integer addOrders(Orders orders) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Orders newOrders = new Orders(orders.getCustomers(), orders.getEmployees(), orders.getShippers(),
				orders.getOrderDate(), null, null, orders.getFreight(), orders.getShipName(), orders.getShipAddress(),
				orders.getShipCity(), orders.getShipRegion(), orders.getShipPostalCode(), orders.getShipCountry(), orders.getOrderDetailses());
		session.beginTransaction();
		Integer result = (Integer) session.save(newOrders);
		session.getTransaction().commit();
		session.close();
		return result;
	}
	
	public static Orders getOrder (Date orderDate) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM OrderDetails " + "WHERE orderDate = :orderDate";
		Query<Orders> query = session.createQuery(hql);
		query.setParameter("orderDate", orderDate);
		Orders result = (Orders) query.getSingleResult();
//		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

}
