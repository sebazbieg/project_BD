package info.projekt.main;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.query.Query;

import info.projekt.dao.CustomersQueries;
import info.projekt.dao.HibernateUtil;
import info.projekt.database.Customers;

public class Main {

	public static void main(String[] args) {
		// Suppliers suppliers = SuppliersQueries.getSupplier();
		// Categories categories = CategoriesQueries.getCustomer();
		// ArrayList list = (ArrayList) CategoriesQueries.categoriesList();
		// Products products = new Products((Categories) list.get(1),
		// suppliers,"1","1.0",1.0,(short) 1,(short) 1,(short) 1,"y");
		// System.out.println(products.getProductName());
		// System.out.println(products.getProductId());
		// ProductsQueries.addProducts(products);
		// ProductsQueries.deleteProducts(products.getProductName());
		// OrdersQueries.OrdersList();
		// }
		Customers customer = CustomersQueries.getCustomer("Around the Horn");

//		ArrayList<Object[]> result = Main.customersList2(customer);
//		for (Object[] objects : result) {
//			for (Object object : objects) {
//				int i = 0;
//				System.out.println(objects[i]);
//			}
//		}
		
		ArrayList<Object[]> result = Main.customersList(customer);
		for (Object[] results : result) {
//		    User user = (User) results[0];
//		    Group group = (Group) results[1];
			System.out.println(results[0]);
			System.out.println(results[1]);
		}

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static ArrayList<Object[]> customersList(Customers cust) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "SELECT o.orderId, SUM(od.unitPrice * od.quantity * (1-od.discount)) FROM Orders o JOIN o.orderDetailses od WHERE o.customers = :customer GROUP BY o ORDER BY SUM(od.unitPrice * od.quantity * (1-od.discount)) desc ";
		Query<Object[]> query = session.createQuery(hql);
		query.setParameter("customer", cust);
		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		session.getTransaction().commit();
		session.close();
		return results;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public static ArrayList<Object[]> customersList2(Customers cust2) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "SELECT p.productName, sum(od.quantity) FROM Orders o JOIN o.orderDetailses od JOIN od.products p WHERE o.customers= :customer GROUP BY p ORDER BY sum(od.quantity) desc";
		Query<Object[]> query = session.createQuery(hql);
		query.setParameter("customer", cust2);
		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		session.getTransaction().commit();
		session.close();
		return results;
	}

}