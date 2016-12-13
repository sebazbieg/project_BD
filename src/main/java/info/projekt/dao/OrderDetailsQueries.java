package info.projekt.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.database.OrderDetails;

public class OrderDetailsQueries {
	
	public static ArrayList<OrderDetails> orderDetailsList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM OrderDetails";
		Query<OrderDetails> query = session.createQuery(hql);
		ArrayList<OrderDetails> results = (ArrayList<OrderDetails>) query.list();
		session.getTransaction().commit();
		session.close();
		// System.out.println("Rozmiar listy to: " + results.size());
		return results;
	}
	
	public static void addOrderDetails(OrderDetails orderDetails) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		OrderDetails newOrderDetails = new OrderDetails(orderDetails.getOrders(), orderDetails.getProducts(),
				orderDetails.getUnitPrice(), orderDetails.getQuantity(), orderDetails.getDiscount());
		session.beginTransaction();
		session.save(newOrderDetails);
		session.getTransaction().commit();
		session.close();
	}

	public static OrderDetails getOrderDetails (Integer orderDetailsId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = " FROM OrderDetails " + "WHERE Id = :orderDetailsId";
		Query<OrderDetails> query = session.createQuery(hql);
		query.setParameter("orderDetailsId", orderDetailsId);
		OrderDetails result = (OrderDetails) query.getSingleResult();
//		System.out.println("Rows affected: " + result);
		session.getTransaction().commit();
		session.close();
		return result;
	}
}
