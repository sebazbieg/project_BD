package info.projekt.gui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ShipperModel {

		private final StringProperty shipperName;
	
		public ShipperModel() {
			this(null);
		}

		public ShipperModel(String shipperName) {
			this.shipperName = new SimpleStringProperty(shipperName);
		}

		public String getShippperName() {
			return shipperName.get();
		}

		public void setShipperName(String shipperName) {
			this.shipperName.set(shipperName);
		}

		public StringProperty shipperNameProperty() {
			return shipperName;
		}
}