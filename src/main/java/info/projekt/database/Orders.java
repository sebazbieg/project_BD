package info.projekt.database;
// Generated 2017-01-07 11:30:43 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Orders generated by hbm2java
 */
@SuppressWarnings("serial")
public class Orders implements java.io.Serializable {

	private Integer orderId;
	private Customers customers;
	private Employees employees;
	private Shippers shippers;
	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	private double freight;
	private String shipName;
	private String shipAddress;
	private String shipCity;
	private String shipRegion;
	private String shipPostalCode;
	private String shipCountry;
	private Set<OrderDetails> orderDetailses = new HashSet<OrderDetails>(0);
	private Set<OrderDetails> orderDetailses_1 = new HashSet<OrderDetails>(0);

	public Orders() {
	}

	public Orders(Customers customers, Employees employees, Shippers shippers, Date orderDate, double freight,
			String shipName, String shipAddress, String shipCity, String shipRegion, String shipPostalCode,
			String shipCountry) {
		this.customers = customers;
		this.employees = employees;
		this.shippers = shippers;
		this.orderDate = orderDate;
		this.freight = freight;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipCity = shipCity;
		this.shipRegion = shipRegion;
		this.shipPostalCode = shipPostalCode;
		this.shipCountry = shipCountry;
	}

	public Orders(Customers customers, Employees employees, Shippers shippers, Date orderDate, Date requiredDate,
			Date shippedDate, double freight, String shipName, String shipAddress, String shipCity, String shipRegion,
			String shipPostalCode, String shipCountry, Set<OrderDetails> orderDetailses,
			Set<OrderDetails> orderDetailses_1) {
		this.customers = customers;
		this.employees = employees;
		this.shippers = shippers;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.freight = freight;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipCity = shipCity;
		this.shipRegion = shipRegion;
		this.shipPostalCode = shipPostalCode;
		this.shipCountry = shipCountry;
		this.orderDetailses = orderDetailses;
		this.orderDetailses_1 = orderDetailses_1;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Customers getCustomers() {
		return this.customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public Employees getEmployees() {
		return this.employees;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}

	public Shippers getShippers() {
		return this.shippers;
	}

	public void setShippers(Shippers shippers) {
		this.shippers = shippers;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return this.requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public Date getShippedDate() {
		return this.shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public double getFreight() {
		return this.freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public String getShipName() {
		return this.shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getShipAddress() {
		return this.shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getShipCity() {
		return this.shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipRegion() {
		return this.shipRegion;
	}

	public void setShipRegion(String shipRegion) {
		this.shipRegion = shipRegion;
	}

	public String getShipPostalCode() {
		return this.shipPostalCode;
	}

	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode = shipPostalCode;
	}

	public String getShipCountry() {
		return this.shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	public Set<OrderDetails> getOrderDetailses() {
		return this.orderDetailses;
	}

	public void setOrderDetailses(Set<OrderDetails> orderDetailses) {
		this.orderDetailses = orderDetailses;
	}

	public Set<OrderDetails> getOrderDetailses_1() {
		return this.orderDetailses_1;
	}

	public void setOrderDetailses_1(Set<OrderDetails> orderDetailses_1) {
		this.orderDetailses_1 = orderDetailses_1;
	}

}
