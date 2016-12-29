package info.projekt.gui.view;

import info.projekt.dao.CustomersQueries;
import info.projekt.database.Customers;
import info.projekt.database.Orders;
import info.projekt.gui.model.CustomerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CustomerOverviewController {

	@FXML
	private TableView<CustomerModel> customerTable;
	@FXML
	private TableColumn<CustomerModel, String> customerNameColumn;

	private Stage dialogStage;
	private boolean okClicked = false;
	private Orders order;
	private String customerName;

	public CustomerOverviewController() {
	}

	@FXML
	private void initialize() {
		customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setOrderEditDialogController(OrderEditDialogController orderEditDialogController) {
		customerTable.setItems(orderEditDialogController.getCustomersData());
	}

	public String getCustomerName() {
		return customerName;
	}

	@FXML
	private void handleAdd() {
		int selectedIndex = customerTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			CustomerModel selectedItem = customerTable.getSelectionModel().getSelectedItem();
			String name = selectedItem.getCustomerName();
			Customers tempCustomer = CustomersQueries.getCustomer(name);
			order = OrderEditDialogController.getOrder();
			order.setCustomers(tempCustomer);
			customerName = tempCustomer.getCompanyName();
			okClicked = true;
			dialogStage.close();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			// alert.initOwner(addProductToOrderTable.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Customer Selected");
			alert.setContentText("Please select a customer in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public boolean isOkClicked() {
		return okClicked;
	}

}