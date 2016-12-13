package info.projekt.gui.view;

import java.util.ArrayList;

import info.projekt.dao.OrderDetailsQueries;
import info.projekt.dao.OrdersQueries;
import info.projekt.database.OrderDetails;
import info.projekt.database.Orders;
import info.projekt.gui.MainAppGui;
import info.projekt.gui.model.OrderModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class OrderOverviewController {
	@FXML
	private TableView<OrderModel> orderTable;
	@FXML
	private TableColumn<OrderModel, Integer> orderIdColumn;
	@FXML
	private TableColumn<OrderModel, Object> customerIdColumn;
	@FXML
	private TableColumn<OrderModel, Object> employeeIdColumn;
	@FXML
	private TableColumn<OrderModel, Object> shipperIdColumn;
	@FXML
	private TableColumn<OrderModel, Object> orderDateColumn;
	@FXML
	private TableColumn<OrderModel, Object> requiredDateColumn;
	@FXML
	private TableColumn<OrderModel, Object> shippedDateColumn;
	@FXML
	private TableColumn<OrderModel, Double> freightColumn;
	@FXML
	private TableColumn<OrderModel, String> shipNameColumn;
	@FXML
	private TableColumn<OrderModel, String> shipAddressColumn;
	@FXML
	private TableColumn<OrderModel, String> shipCityColumn;
	@FXML
	private TableColumn<OrderModel, String> shipRegionColumn;
	@FXML
	private TableColumn<OrderModel, String> shipPostalCodeColumn;
	@FXML
	private TableColumn<OrderModel, String> shipCountryColumn;

	@FXML
	private Label orderIdLabel;
	@FXML
	private Label customerIdLabel;
	@FXML
	private Label employeeIdLabel;
	@FXML
	private Label shipperIdLabel;
	@FXML
	private Label orderDateLabel;
	@FXML
	private Label requiredDateLabel;
	@FXML
	private Label shippedDateLabel;
	@FXML
	private Label freightLabel;
	@FXML
	private Label shipNameLabel;
	@FXML
	private Label shipAddressLabel;
	@FXML
	private Label shipCityLabel;
	@FXML
	private Label shipRegionLabel;
	@FXML
	private Label shipPostalCodeLabel;
	@FXML
	private Label shipCountryLabel;

	private MainAppGui mainAppGui;

	public OrderOverviewController() {
	}

	@FXML
	private void initialize() {
		orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty().asObject());
//		customerIdColumn.setCellValueFactory(cellData -> cellData.getValue().customersProperty().asObject());
//		employeeIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeesIdProperty().asObject());
//		shipperIdColumn.setCellValueFactory(cellData -> cellData.getValue().shippersProperty().asObject());
//		orderDateColumn.setCellValueFactory(cellData -> cellData.getValue().orderDateProperty().asString());
//		requiredDateColumn.setCellValueFactory(cellData -> cellData.getValue().requiredDateProperty().asObject());
//		shippedDateColumn.setCellValueFactory(cellData -> cellData.getValue().shippedDateProperty());

		freightColumn.setCellValueFactory(cellData -> cellData.getValue().freightProperty().asObject());
		shipNameColumn.setCellValueFactory(cellData -> cellData.getValue().shipNameProperty());
		shipAddressColumn.setCellValueFactory(cellData -> cellData.getValue().shipAddressProperty());
		shipCityColumn.setCellValueFactory(cellData -> cellData.getValue().shipCityProperty());
		shipRegionColumn.setCellValueFactory(cellData -> cellData.getValue().shipRegionProperty());
		shipPostalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().shipPostalCodeProperty());
		shipCountryColumn.setCellValueFactory(cellData -> cellData.getValue().shipCountryProperty());
	}

	public void setMainAppGUI(MainAppGui mainAppGui) {
		this.mainAppGui = mainAppGui;
		orderTable.setItems(mainAppGui.getOrderData());
	}

	@FXML
	private void handleDeleteOrder() {
		int selectedIndex = orderTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			OrderModel selectedItem = orderTable.getSelectionModel().getSelectedItem();
			Integer id = selectedItem.getOrderId();
			OrdersQueries.deleteOrders(id);
			orderTable.getItems().remove(selectedIndex);

		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(mainAppGui.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void handleNewOrder() {
		Orders tempOrder = new Orders();
		ArrayList<OrderDetails> tempList = new ArrayList<OrderDetails>();
		
		boolean okClicked = mainAppGui.showOrderEditDialog(tempOrder);
		if (okClicked) {
			mainAppGui.getOrderData().removeAll(mainAppGui.getOrderData());
			tempOrder = OrderEditDialogController.getOrder();
			Integer orderId = OrdersQueries.addOrders(tempOrder);
			mainAppGui.setOrderList(OrdersQueries.OrdersList());
			mainAppGui.refreshOrderOverview();
			System.out.println(tempOrder.getOrderDate());
			tempOrder.setOrderId(orderId);
			tempList = mainAppGui.getOrderDetailsList();
			for (int i = 0; i < tempList.size(); i++) {
				OrderDetails temp = tempList.get(i);
				temp.setOrders(tempOrder);
				OrderDetailsQueries.addOrderDetails(temp);
			}
//			tempList.clear();
//			mainAppGui.setOrderDetailsList(tempList);
//			tempList = AddProductToOrderDialogController.getOrderDetailsList();
//			tempList.clear();
			AddProductToOrderDialogController.orderDetailsListClear();
//			mainAppGui.setOrderDetailsList(AddProductToOrderDialogController.getOrderDetailsList());
//			mainAppGui.refreshOrderDetails();
			mainAppGui.getOrderDetailsDataClear();
			
			
			
			
		}

	}
}
