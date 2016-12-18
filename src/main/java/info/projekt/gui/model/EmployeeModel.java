package info.projekt.gui.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmployeeModel {

		private final StringProperty employeeFirstName;
		private final StringProperty employeeLastName;
		private final StringProperty employeeTitle;
	
		public EmployeeModel() {
			this(null, null, null);
		}

		public EmployeeModel(String employeeFirstName, String employeeLastName, String employeeTitle) {
			this.employeeFirstName = new SimpleStringProperty(employeeFirstName);
			this.employeeLastName = new SimpleStringProperty(employeeLastName);
			this.employeeTitle = new SimpleStringProperty(employeeTitle);
		}

		public String getEmployeeFirstName() {
			return employeeFirstName.get();
		}

		public void setEmployeeFirstName(String employeeFirstName) {
			this.employeeFirstName.set(employeeFirstName);
		}

		public StringProperty employeeFirstNameProperty() {
			return employeeFirstName;
		}
		
		public String getEmployeeLastName() {
			return employeeLastName.get();
		}

		public void setEmployeeLastName(String employeeLastName) {
			this.employeeLastName.set(employeeLastName);
		}

		public StringProperty employeeLastNameProperty() {
			return employeeLastName;
		}
		
		public String getEmployeeTitle() {
			return employeeTitle.get();
		}

		public void setEmployeeTitle(String employeeTitle) {
			this.employeeTitle.set(employeeTitle);
		}

		public StringProperty employeeTitleProperty() {
			return employeeTitle;
		}
}