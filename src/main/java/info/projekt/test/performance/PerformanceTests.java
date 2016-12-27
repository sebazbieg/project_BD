package info.projekt.test.performance;

import org.hibernate.Session;

import info.seba.projekt.dao.HibernateUtil;
import info.seba.projekt.dao.TableQueries;
import info.seba.projekt.database.Table;

public class PerformanceTests {
	public static void main(String[] args) {
	/*	long start = System.currentTimeMillis();
		for (int i = 1; i <= 1000; i++) {
			TableQueries.addTable();
		}
		long stop = System.currentTimeMillis();
		System.out.println("Czas wykonania dodania rekordow:" + (stop - start) + "ms");*/

	/*	List<Table> lista = new ArrayList<Table>();
		for (int i = 1; i <= 1000; i++) {
			lista.add(TableQueries.addTable());
		}*/

		Session session = HibernateUtil.getSessionFactory().openSession();
		Table newTable = new Table("bum", "bumbum", "bymbym");
		long start3 = System.currentTimeMillis();
		session.beginTransaction();
		for (int i = 1; i <= 1000; i++) {
			session.save(newTable);
			System.out.println("dodano rekord");
		}
		session.getTransaction().commit();
		session.close();
		long stop3 = System.currentTimeMillis();
		System.out.println("Czas wykonania dodania rekordow:" + (stop3 - start3) + "ms");

		long start2 = System.currentTimeMillis();
		TableQueries.deleteCustomers();
		long stop2 = System.currentTimeMillis();
		System.out.println("Czas usuwania dodanych rekordow:" + (stop2 - start2) + "ms");

	}
}
