package info.projekt.gui.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductsRaportModel {
	
	private final StringProperty customerName;
	private final LongProperty quantity;

	public ProductsRaportModel() {
		this(null, null);
	}

	public ProductsRaportModel(String customerName, Long quantity) {
		this.customerName = new SimpleStringProperty(customerName);
		this.quantity = new SimpleLongProperty(quantity);
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
	
	public Long getQuantity() {
		return quantity.get();
	}

	public void setQuantity(Long quantity) {
		this.quantity.set(quantity);
	}

	public LongProperty quantityProperty() {
		return quantity;
	}

}
