package info.projekt.gui.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CustomersRaportModel {

	private final IntegerProperty orderId;
	private final DoubleProperty totalPrice;

	public CustomersRaportModel() {
		this(null, null);
	}

	public CustomersRaportModel(Integer orderId, Double totalPrice) {
		this.orderId = new SimpleIntegerProperty(orderId);
		this.totalPrice = new SimpleDoubleProperty(totalPrice);
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

	public Double getTotalPrice() {
		return totalPrice.get();
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice.set(totalPrice);
	}

	public DoubleProperty totalPriceProperty() {
		return totalPrice;
	}

}
