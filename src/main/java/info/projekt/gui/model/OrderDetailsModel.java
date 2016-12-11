package info.projekt.gui.model;

import info.projekt.database.Orders;
import info.projekt.database.Products;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class OrderDetailsModel {

	private final IntegerProperty orderDetailsId;
	private final ObjectProperty<Orders> orders;
	private final ObjectProperty<Products> products;
	private final DoubleProperty unitPrice;
	private final IntegerProperty quantity;
	private final FloatProperty discount;

	public OrderDetailsModel(Integer orderDetailsId, Orders orders, Products products,
			Double unitPrice, Short quantity, Float discount) {
		this.orderDetailsId = new SimpleIntegerProperty(orderDetailsId);
		this.orders = new SimpleObjectProperty<Orders>(orders);
		this.products = new SimpleObjectProperty<Products>(products);
		this.unitPrice = new SimpleDoubleProperty(unitPrice);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.discount = new SimpleFloatProperty(discount);
	}
	
	public Integer getOrderDetailsId() {
		return orderDetailsId.get();
	}

	public void setOrderDetailsId(Integer orderDetailsId) {
		this.orderDetailsId.set(orderDetailsId);
	}

	public IntegerProperty orderDetailsIdProperty() {
		return orderDetailsId;
	}
	
	public Orders getOrders() {
		return orders.get();
	}

	public void setOrders(Orders orders) {
		this.orders.set(orders);
	}

	public ObjectProperty<Orders> ordersProperty() {
		return orders;
	}
	
	public Products getProducts() {
		return products.get();
	}

	public void setProducts(Products products) {
		this.products.set(products);
	}

	public ObjectProperty<Products> productsProperty() {
		return products;
	}
	
	public Double getUnitPrice() {
		return unitPrice.get();
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice.set(unitPrice);
	}

	public DoubleProperty unitPriceProperty() {
		return unitPrice;
	}
	
	public Integer getQuantity() {
		return quantity.get();
	}

	public void setQuantity(Integer quantity) {
		this.quantity.set(quantity);
	}

	public IntegerProperty quantityProperty() {
		return quantity;
	}
	
	public Float getDiscount() {
		return discount.get();
	}

	public void setDiscount(Float discount) {
		this.discount.set(discount);
	}

	public FloatProperty discountProperty() {
		return discount;
	}
}
