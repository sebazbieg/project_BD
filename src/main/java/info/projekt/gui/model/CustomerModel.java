package info.projekt.gui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerModel {

	private final StringProperty customerName;

	public CustomerModel() {
		this(null);
	}

	public CustomerModel(String customerName) {
		this.customerName = new SimpleStringProperty(customerName);
	}

	public String getCustomerName() {
		return customerName.get();
	}

	public void setCustomerName(String customerName) {
		this.customerName.set(customerName);
	}

	public StringProperty customerNameProperty() {
		return customerName;
	}
}
