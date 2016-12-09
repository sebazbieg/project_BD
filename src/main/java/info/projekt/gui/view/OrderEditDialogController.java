package info.projekt.gui.view;

import java.util.ArrayList;
import java.util.Date;
import info.projekt.dao.CustomersQueries;
import info.projekt.dao.EmployeesQueries;
import info.projekt.dao.OrdersQueries;
import info.projekt.database.Customers;
import info.projekt.database.Employees;
import info.projekt.database.Orders;
import info.projekt.gui.MainAppGui;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class OrderEditDialogController {
	@FXML
	private TextField freightField;
	@FXML
	private TextField shipNameField;
	@FXML
	private TextField shipAddressField;
	@FXML
	private TextField shipCityField;
	@FXML
	private TextField shipRegionField;
	@FXML
	private TextField shipPostalCodeField;
	@FXML
	private TextField shipCountryField;

	private Stage dialogStage;
	private static Orders order;
	private boolean okClicked = false;
	private MainAppGui mainAppGui;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the person to be edited in the dialog.
	 * 
	 * @param person
	 */

	public static Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		OrderEditDialogController.order = order;

		freightField.setText(Double.toString(order.getFreight()));
		shipNameField.setText(order.getShipName());
		shipAddressField.setText(order.getShipAddress());
		shipCityField.setText(order.getShipCity());
		shipRegionField.setText(order.getShipRegion());
		shipPostalCodeField.setText(order.getShipPostalCode());
		shipCountryField.setText(order.getShipCountry());
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			getOrder().setFreight(Double.parseDouble(freightField.getText()));
			getOrder().setShipName(shipNameField.getText());
			getOrder().setShipAddress(shipAddressField.getText());
			getOrder().setShipCity(shipCityField.getText());
			getOrder().setShipRegion(shipRegionField.getText());
			getOrder().setShipPostalCode(shipPostalCodeField.getText());
			getOrder().setShipCountry(shipCountryField.getText());
			ArrayList<Customers> list = CustomersQueries.customersList();
			ArrayList<Employees> list2 = EmployeesQueries.employeesList();
			getOrder().setCustomers((Customers) list.get(1));
			getOrder().setEmployees((Employees) list2.get(1));
			Date date = new Date();
			getOrder().setOrderDate(date);

			okClicked = true;
			dialogStage.close();
		}
	}
	
	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	@FXML
	private void handleAddProduct(){
//		Orders tempOrder = new Orders();

	/*	boolean okClicked =*/ mainAppGui.showAddProductToOrderDialogController(/*tempOrder*/);
		if (okClicked) {
//			mainAppGui.getOrderData().removeAll(mainAppGui.getOrderData());
//			OrdersQueries.addOrder(OrderEditDialogController.getOrder());
//			mainAppGui.setOrderList(OrdersQueries.OrdersList());
//			mainAppGui.refreshOrderOverview();
		}
	}
	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";
		if (freightField.getText() == null || freightField.getText().length() == 0) {
			errorMessage += "Podaj koszt dostawy!\n";
		} else {
			try {
				Double.parseDouble(freightField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Podaj cenę w formacie zł.gr!\n";
			}
		}
		
		if (shipNameField.getText() == null || shipNameField.getText().length() == 0) {
			errorMessage += "Podaj nazwę odbiorcy!\n";
		}
		
		if (shipAddressField.getText() == null || shipAddressField.getText().length() == 0) {
				errorMessage += "Podaj adres nadawcy!\n";
		}
		
		if (shipCityField.getText() == null || shipCityField.getText().length() == 0) {
					errorMessage += "Podaj miasto nadawcy!\n";
		}
		
		if (shipRegionField.getText() == null || shipRegionField.getText().length() == 0) {
						errorMessage += "Podaj region nadawcy!\n";
		}
		
		if (shipPostalCodeField.getText() == null || shipPostalCodeField.getText().length() == 0) {
							errorMessage += "Podaj kod pocztowy nadawcy!\n";
		}
		
		if (shipCountryField.getText() == null || shipCountryField.getText().length() == 0) {
								errorMessage += "Podaj kraj nadawcy!\n";
		}
		
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}