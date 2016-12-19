package info.projekt.gui.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import info.projekt.dao.CustomersQueries;
import info.projekt.dao.EmployeesQueries;
import info.projekt.dao.ProductsQueries;
import info.projekt.dao.ShippersQueries;
import info.projekt.database.Customers;
import info.projekt.database.Employees;
import info.projekt.database.OrderDetails;
import info.projekt.database.Orders;
import info.projekt.database.Products;
import info.projekt.database.Shippers;
import info.projekt.gui.MainAppGui;
import info.projekt.gui.model.CustomerModel;
import info.projekt.gui.model.EmployeeModel;
import info.projekt.gui.model.OrderDetailsModel;
import info.projekt.gui.model.ProductModel;
import info.projekt.gui.model.ShipperModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
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

	@FXML
	private TableView<OrderDetailsModel> orderDetailsTable;
	@FXML
	private TableColumn<OrderDetailsModel, String> productNameColumn;
	@FXML
	private TableColumn<OrderDetailsModel, Double> unitPriceColumn;
	@FXML
	private TableColumn<OrderDetailsModel, Integer> quantityColumn;
	@FXML
	private TableColumn<OrderDetailsModel, Float> discountColumn;

	@FXML
	private Label orderDetailsIdLabel;
	@FXML
	private Label ordersIdLabel;
	@FXML
	private Label productIdLabel;
	@FXML
	private Label unitPriceLabel;
	@FXML
	private Label quantityLabel;
	@FXML
	private Label discountLabel;
	@FXML
	private Label customerLabel;
	@FXML
	private Label employeeLabel;
	@FXML
	private Label shipperLabel;

	private Stage dialogStage;
	private static Orders order;
	private boolean okClicked = false;
	private MainAppGui mainAppGui;

	private ObservableList<ProductModel> productData = FXCollections.observableArrayList();
	private ArrayList<Products> productList = ProductsQueries.ProductsList();
	private ObservableList<ShipperModel> shipperData = FXCollections.observableArrayList();
	private ArrayList<Shippers> shipperList = ShippersQueries.shippersList();
	private ObservableList<CustomerModel> customersData = FXCollections.observableArrayList();
	private ArrayList<Customers> customersList = CustomersQueries.customersList();
	private ObservableList<EmployeeModel> employeesData = FXCollections.observableArrayList();
	private ArrayList<Employees> employeesList = EmployeesQueries.employeesList();

	public OrderEditDialogController() {
		refreshProductOverview();
		refreshShippersOverview();
		refreshCustomerOverview();
		refreshEmployeeOverview();
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		
		productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productsNameProperty());
		quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
		unitPriceColumn.setCellValueFactory(cellData -> cellData.getValue().unitPriceProperty().asObject());
		discountColumn.setCellValueFactory(cellData -> cellData.getValue().discountProperty().asObject());
	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

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

	public void setMainAppGui(MainAppGui mainAppGui) {
		this.mainAppGui = mainAppGui;
		orderDetailsTable.setItems(mainAppGui.getOrderDetailsData());
	}

	public void setOrder2(Orders order) {
		OrderEditDialogController.order = order;
	}

	public ObservableList<ProductModel> getProductData() {
		return productData;
	}

	public ObservableList<ShipperModel> getShipperData() {
		return shipperData;
	}

	public ObservableList<CustomerModel> getCustomersData() {
		return customersData;
	}

	public ObservableList<EmployeeModel> getEmployeeData() {
		return employeesData;
	}

	public void refreshProductOverview() {
		for (int i = 0; i < productList.size(); i++) {
			Products tempProduct = productList.get(i);
			productData.add(new ProductModel(tempProduct.getProductId(), tempProduct.getProductName(), null, null,
					tempProduct.getQuantityPerUnit(), tempProduct.getUnitPrice(),
					Integer.valueOf(tempProduct.getUnitsInStock()), Integer.valueOf(tempProduct.getUnitsOnOrder()),
					Integer.valueOf(tempProduct.getReorderLevel()), tempProduct.getDiscontinued()));
		}
	}

	public void refreshShippersOverview() {
		for (int i = 0; i < shipperList.size(); i++) {
			Shippers tempShipper = shipperList.get(i);
			shipperData.add(new ShipperModel(tempShipper.getCompanyName()));
		}
	}

	public void refreshEmployeeOverview() {
		for (int i = 0; i < employeesList.size(); i++) {
			Employees tempEmployee = employeesList.get(i);
			employeesData.add(new EmployeeModel(tempEmployee.getFirstName(), tempEmployee.getLastName(),
					tempEmployee.getTitle()));
			System.out.println(employeesData.get(i).getEmployeeFirstName());
		}
	}

	public void refreshCustomerOverview() {
		for (int i = 0; i < customersList.size(); i++) {
			Customers tempCustomer = customersList.get(i);
			customersData.add(new CustomerModel(tempCustomer.getCompanyName()));
		}
	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	public boolean showAddProductToOrderDialog(OrderDetails orderDetails) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/AddProductToOrderDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Add Product to Order");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			// dialogStage.initOwner(mainAppGui.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			AddProductToOrderDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setOrderDetails(orderDetails);
			controller.setOrderEditDialogController(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String showAddCustomerToOrderDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/CustomerOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Add Customer to Order");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			// dialogStage.initOwner(mainAppGui.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			CustomerOverviewController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setOrderEditDialogController(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.getCustomerName();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String showAddEmployeeToOrderDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/EmployeeOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Add Employee to Order");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			// dialogStage.initOwner(mainAppGui.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			EmployeeOverviewController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setOrderEditDialogController(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			
			return controller.getEmployeeFirstName()+" "+controller.getEmployeeLastName();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String showAddShipperToOrderDialog() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/ShipperOverview.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Add Shipper to Order");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			// dialogStage.initOwner(mainAppGui.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			ShipperOverviewController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setOrderEditDialogController(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.getShipperName();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@FXML
	private void handleAddCustomer() {
//		showAddCustomerToOrderDialog();
		customerLabel.setText(showAddCustomerToOrderDialog());
	}

	@FXML
	private void handleAddEmployee() {
//		showAddEmployeeToOrderDialog();
		employeeLabel.setText(showAddEmployeeToOrderDialog());
	}

	@FXML
	private void handleAddShipper() {
//		showAddShipperToOrderDialog();
		shipperLabel.setText(showAddShipperToOrderDialog());
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
	private void handleAddProduct() {
		OrderDetails tempOrderDetails = new OrderDetails();
		boolean okClicked = showAddProductToOrderDialog(tempOrderDetails);
		if (okClicked) {
			mainAppGui.getOrderDetailsData().removeAll(mainAppGui.getOrderDetailsData());
			mainAppGui.setOrderDetailsList(AddProductToOrderDialogController.getOrderDetailsList());
			mainAppGui.refreshOrderDetails();
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
		
		if (getOrder().getCustomers() == null) {
			errorMessage += "Wybierz Klienta!\n";
		}
		
		if (getOrder().getEmployees() == null) {
			errorMessage += "Wybierz Pracownika!\n";
		}
		
		if (getOrder().getShippers() == null) {
			errorMessage += "Wybierz Spedytora!\n";
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