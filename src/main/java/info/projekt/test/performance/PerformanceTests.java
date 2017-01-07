package info.projekt.test.performance;

import org.hibernate.Session;

import info.projekt.dao.OrdersQueries;
import info.projekt.database.Orders;
import info.projekt.dao.HibernateUtil;

public class PerformanceTests {
	public static void main(String[] args) {

		Orders tempOrder = OrdersQueries.getOrder(11000);
		tempOrder = new Orders(tempOrder.getCustomers(), tempOrder.getEmployees(), tempOrder.getShippers(),
				tempOrder.getOrderDate(), null, null, 55.13, tempOrder.getShipName(), tempOrder.getShipAddress(),
				tempOrder.getShipCity(), tempOrder.getShipRegion(), tempOrder.getShipPostalCode(),
				tempOrder.getShipCountry(), tempOrder.getOrderDetailses(), null);

		long start = System.currentTimeMillis();
		for (int i = 1; i <= 1000; i++) {
			OrdersQueries.addOrders(tempOrder);
		}
		long stop = System.currentTimeMillis();
		System.out.println("Czas wykonania dodawania 1000 rekordow w oddzielnych sesjach:	" + (stop - start) + "ms");

		long start3 = System.currentTimeMillis();
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (int i = 1; i <= 1000; i++) {
			session.save(tempOrder);
			if (i % 1 == 0) { // 20, same as the JDBC batch size
				// flush a batch of inserts and release memory:
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
		session.close();
		long stop3 = System.currentTimeMillis();
		System.out.println("Czas wykonania dodawania 1000 rekordow w jednej sesji:	" + (stop3 - start3) + "ms");

		long start2 = System.currentTimeMillis();
		OrdersQueries.OrdersList();
		long stop2 = System.currentTimeMillis();
		System.out.println("Czas pobierania wszsytkich rekordów z bazy danych:	" + (stop2 - start2) + "ms");

		long start4 = System.currentTimeMillis();
		OrdersQueries.deleteOrdersByFreight(55.13);
		long stop4 = System.currentTimeMillis();
		System.out.println("Czas usuwania dodanych rekordow:	" + (stop4 - start4) + "ms");

	}
}
