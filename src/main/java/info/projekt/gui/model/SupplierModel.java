package info.projekt.gui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SupplierModel {

		private final StringProperty supplierName;

		public SupplierModel() {
			this(null);
		}

		public SupplierModel(String supplierName) {
			this.supplierName = new SimpleStringProperty(supplierName);
		}

		public String getSupplierName() {
			return supplierName.get();
		}

		public void setSupplierName(String supplierName) {
			this.supplierName.set(supplierName);
		}

		public StringProperty supplierNameProperty() {
			return supplierName;
		}
}