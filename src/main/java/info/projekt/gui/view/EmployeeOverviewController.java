package info.projekt.gui.view;

import info.projekt.dao.EmployeesQueries;
import info.projekt.database.Employees;
import info.projekt.database.Orders;
import info.projekt.gui.model.EmployeeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class EmployeeOverviewController {

	@FXML
	private TableView<EmployeeModel> employeeTable;
	@FXML
	private TableColumn<EmployeeModel, String> employeeFirstNameColumn;
	@FXML
	private TableColumn<EmployeeModel, String> employeeLastNameColumn;
	@FXML
	private TableColumn<EmployeeModel, String> employeeTitleColumn;

	private Stage dialogStage;
	private boolean okClicked = false;
	Orders order;
	String employeeFirstName;
	String employeeLastName;

	public EmployeeOverviewController() {
	}

	@FXML
	private void initialize() {
		employeeFirstNameColumn.setCellValueFactory(cellData -> cellData.getValue().employeeFirstNameProperty());
		employeeLastNameColumn.setCellValueFactory(cellData -> cellData.getValue().employeeLastNameProperty());
		employeeTitleColumn.setCellValueFactory(cellData -> cellData.getValue().employeeTitleProperty());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setOrderEditDialogController(OrderEditDialogController orderEditDialogController) {
		employeeTable.setItems(orderEditDialogController.getEmployeeData());
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	@FXML
	private void handleAdd() {
		int selectedIndex = employeeTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			EmployeeModel selectedItem = employeeTable.getSelectionModel().getSelectedItem();
			String firstName = selectedItem.getEmployeeFirstName();
			String lastName = selectedItem.getEmployeeLastName();
			String title = selectedItem.getEmployeeTitle();
			Employees tempEmployee = EmployeesQueries.getEmployee(firstName, lastName, title);
			order = OrderEditDialogController.getOrder();
			order.setEmployees(tempEmployee);
			employeeFirstName = tempEmployee.getFirstName();
			employeeLastName = tempEmployee.getLastName();
			okClicked = true;
			dialogStage.close();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			// alert.initOwner(addProductToOrderTable.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Employee Selected");
			alert.setContentText("Please select a employee in the table.");

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