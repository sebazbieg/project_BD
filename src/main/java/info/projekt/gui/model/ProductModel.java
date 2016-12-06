package info.projekt.gui.model;

import info.projekt.database.Categories;
import info.projekt.database.Suppliers;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class ProductModel {

	private final IntegerProperty productId;
	private final StringProperty productName;
	private final ObjectProperty<Suppliers> supplierId;
	private final ObjectProperty<Categories> categoryId;
	private final StringProperty quantityPerUnit;
	private final DoubleProperty unitPrice;
	private final IntegerProperty unitsInStock;
	private final IntegerProperty unitsOnOrder;
	private final IntegerProperty reorderLevel;
	private final StringProperty discontinued;

	/**
	 * Default constructor.
	 */
	/*
	 * public ProductModel() { this(null, null, null, null, null, null); }
	 */
	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public ProductModel(Integer productId, String productName, Suppliers supplierId, Categories categoryId,
			String quantityPerUnit, Double unitPrice, Integer unitsInStock, Integer unitsOnOrder, Integer reorderLevel,
			String discontinued) {

		this.productId = new SimpleIntegerProperty(productId);
		this.productName = new SimpleStringProperty(productName);
		this.supplierId = new SimpleObjectProperty<Suppliers>(supplierId);
		this.categoryId = new SimpleObjectProperty<Categories>(categoryId);
		this.quantityPerUnit = new SimpleStringProperty(quantityPerUnit);
		this.unitPrice = new SimpleDoubleProperty(unitPrice);
		this.unitsInStock = new SimpleIntegerProperty(unitsInStock);
		this.unitsOnOrder = new SimpleIntegerProperty(unitsOnOrder);
		this.reorderLevel = new SimpleIntegerProperty(reorderLevel);
		this.discontinued = new SimpleStringProperty(discontinued);
	}

	public Integer getProductId() {
		return productId.get();
	}

	public void setProductId(Integer productId) {
		this.productId.set(productId);
	}

	public IntegerProperty productIdProperty() {
		return productId;
	}

	public String getProductName() {
		return productName.get();
	}

	public void setProductName(String productName) {
		this.productName.set(productName);
	}

	public StringProperty productNameProperty() {
		return productName;
	}

	public Suppliers getSupplierId() {
		return supplierId.get();
	}

	public void setSupplierId(Suppliers supplierId) {
		this.supplierId.set(supplierId);
	}

	public ObjectProperty<Suppliers> supplierIdProperty() {
		return supplierId;
	}
	
	public Categories getCategoryId() {
		return categoryId.get();
	}

	public void setCategoryId(Categories categoryId) {
		this.categoryId.set(categoryId);
	}

	public ObjectProperty<Categories> categoryIdProperty() {
		return categoryId;
	}
	
	public String getQuantityPerUnit() {
		return productName.get();
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit.set(quantityPerUnit);
	}

	public StringProperty quantityPerUnitProperty() {
		return quantityPerUnit;
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
	
	public Integer getUnitsInStock() {
		return unitsInStock.get();
	}

	public void setUnitsInStock(Integer unitsInStock) {
		this.unitsInStock.set(unitsInStock);
	}

	public IntegerProperty unitsInStockProperty() {
		return unitsInStock;
	}
	
	public Integer getUnitsOnOrder() {
		return unitsOnOrder.get();
	}

	public void setUnitsOnOrder(Integer unitsOnOrder) {
		this.unitsOnOrder.set(unitsOnOrder);
	}

	public IntegerProperty unitsOnOrderProperty() {
		return unitsOnOrder;
	}
	
	public Integer getReorderLevel() {
		return reorderLevel.get();
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel.set(reorderLevel);
	}

	public IntegerProperty reorderLevelProperty() {
		return reorderLevel;
	}
	
	public String getDiscontinued() {
		return discontinued.get();
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued.set(discontinued);
	}

	public StringProperty discontinuedProperty() {
		return discontinued;
	}
}