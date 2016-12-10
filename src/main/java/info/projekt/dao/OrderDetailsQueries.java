package info.projekt.dao;

import org.hibernate.Session;

import info.projekt.database.OrderDetails;

public class OrderDetailsQueries {
	public static void addOrderDetails(OrderDetails orderDetails) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		OrderDetails newOrderDetails = new OrderDetails(orderDetails.getOrders(), orderDetails.getProducts(),
				orderDetails.getUnitPrice(), orderDetails.getQuantity(), orderDetails.getDiscount());
		session.beginTransaction();
		session.save(newOrderDetails);
		session.getTransaction().commit();
		session.close();
	}

}
