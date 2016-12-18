package info.projekt.gui.model;

import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderModel {

	private final IntegerProperty orderId;
	private final StringProperty customersName;
	private final StringProperty employeesName;
	private final StringProperty shippersName;
	private final ObjectProperty<Date> orderDate;
	private final ObjectProperty<Date> requiredDate;
	private final ObjectProperty<Date> shippedDate;
	private final DoubleProperty freight;
	private final StringProperty shipName;
	private final StringProperty shipAddress;
	private final StringProperty shipCity;
	private final StringProperty shipRegion;
	private final StringProperty shipPostalCode;
	private final StringProperty shipCountry;

	public OrderModel(Integer orderId, String customersName, String employeesName, String shippersName, Date orderDate,
			Date requiredDate, Date shippedDate, Double freight, String shipName, String shipAddress, String shipCity,
			String shipRegion, String shipPostalCode, String shipCountry) {
		this.orderId = new SimpleIntegerProperty(orderId);
		this.customersName = new SimpleStringProperty(customersName);
		this.employeesName = new SimpleStringProperty(employeesName);
		this.shippersName = new SimpleStringProperty(shippersName);
		this.orderDate = new SimpleObjectProperty<Date>(orderDate);
		this.requiredDate = new SimpleObjectProperty<Date>(requiredDate);
		this.shippedDate = new SimpleObjectProperty<Date>(shippedDate);
		this.freight = new SimpleDoubleProperty(freight);
		this.shipName = new SimpleStringProperty(shipName);
		this.shipAddress = new SimpleStringProperty(shipAddress);
		this.shipCity = new SimpleStringProperty(shipCity);
		this.shipRegion = new SimpleStringProperty(shipRegion);
		this.shipPostalCode = new SimpleStringProperty(shipPostalCode);
		this.shipCountry = new SimpleStringProperty(shipCountry);
	}

	public Integer getOrderId() {
		return orderId.get();
	}

	public void setOrderId(Integer orderId) {
		this.orderId.set(orderId);
	}

	public IntegerProperty orderIdProperty() {
		return orderId;
	}

	public String getCustomersName() {
		return customersName.get();
	}

	public void setCustomersName(String customersName) {
		this.customersName.set(customersName);
	}

	public StringProperty customersNameProperty() {
		return customersName;
	}

	public String getEmployeesName() {
		return employeesName.get();
	}

	public void setEmployeesName(String employeesName) {
		this.employeesName.set(employeesName);
	}

	public StringProperty employeesNameProperty() {
		return employeesName;
	}

	public String getShippersName() {
		return shippersName.get();
	}

	public void setShippersName(String shippersName) {
		this.shippersName.set(shippersName);
	}

	public StringProperty shippersNameProperty() {
		return shippersName;
	}

	public Date getOrderDate() {
		return orderDate.get();
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate.set(orderDate);
	}

	public ObjectProperty<Date> orderDateProperty() {
		return orderDate;
	}

	public Date getRequiredDate() {
		return requiredDate.get();
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate.set(requiredDate);
	}

	public ObjectProperty<Date> requiredDateProperty() {
		return requiredDate;
	}

	public Date getShippedDate() {
		return shippedDate.get();
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate.set(shippedDate);
	}

	public ObjectProperty<Date> shippedDateProperty() {
		return shippedDate;
	}

	public double getFreight() {
		return freight.get();
	}

	public void setFreight(double freight) {
		this.freight.set(freight);
	}

	public DoubleProperty freightProperty() {
		return freight;
	}

	public String getShipName() {
		return shipName.get();
	}

	public void setShipName(String shipName) {
		this.shipName.set(shipName);
	}

	public StringProperty shipNameProperty() {
		return shipName;
	}

	public String getShipAddress() {
		return shipAddress.get();
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress.set(shipAddress);
	}

	public StringProperty shipAddressProperty() {
		return shipAddress;
	}

	public String getShipCity() {
		return shipCity.get();
	}

	public void setShipCity(String shipCity) {
		this.shipCity.set(shipCity);
	}

	public StringProperty shipCityProperty() {
		return shipCity;
	}

	public String getShipRegion() {
		return shipRegion.get();
	}

	public void setShipRegion(String shipRegion) {
		this.shipRegion.set(shipRegion);
	}

	public StringProperty shipRegionProperty() {
		return shipRegion;
	}

	public String getShipPostalCode() {
		return shipPostalCode.get();
	}

	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode.set(shipPostalCode);
	}

	public StringProperty shipPostalCodeProperty() {
		return shipPostalCode;
	}

	public String getShipCountry() {
		return shipCountry.get();
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry.set(shipCountry);
	}

	public StringProperty shipCountryProperty() {
		return shipCountry;
	}
}
