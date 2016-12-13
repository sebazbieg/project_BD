package info.projekt.gui.view;

import java.util.ArrayList;
import info.projekt.dao.ProductsQueries;
import info.projekt.database.OrderDetails;
import info.projekt.database.Orders;
import info.projekt.database.Products;
import info.projekt.gui.MainAppGui;
import info.projekt.gui.model.ProductModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddProductToOrderDialogController {

	@FXML
	private TableView<ProductModel> addProductToOrderTable;
	@FXML
	private TableColumn<ProductModel, Integer> addProductToOrderIdColumn;
	@FXML
	private TableColumn<ProductModel, String> addProductToOrderNameColumn;
	@FXML
	private TableColumn<ProductModel, Double> addProductToOrderPriceColumn;
	
	@FXML
	private TextField quantityField;
	@FXML 
	private TextField discountField;
	
	public AddProductToOrderDialogController() {
	}
	
    private Stage dialogStage;
    private static OrderDetails orderDetails;
    private boolean okClicked = false;
    private Orders order;
//	private Set<OrderDetails> orderDetailsSet = new HashSet<OrderDetails>(0);
	private static ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
	MainAppGui mainAppGui;
	


	@FXML
	private void initialize() {
		addProductToOrderIdColumn.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
		addProductToOrderNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
		addProductToOrderPriceColumn.setCellValueFactory(cellData -> cellData.getValue().unitPriceProperty().asObject());		
	}
	
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public static ArrayList<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}
    
    public static void orderDetailsListClear() {
    	orderDetailsList.clear();
    }
    
	public static OrderDetails getOrderDetails() {
		return orderDetails;
	}
    
	public void setOrderEditDialogController(OrderEditDialogController orderEditDialogController) {
		addProductToOrderTable.setItems(orderEditDialogController.getProductData());
	}
    
    public void setOrderDetails(OrderDetails orderDetails) {
    	AddProductToOrderDialogController.orderDetails = orderDetails;
    	
    	quantityField.setText(Short.toString(orderDetails.getQuantity()));
    	discountField.setText(Float.toString(orderDetails.getDiscount()));
    }
    
//	public Set<OrderDetails> getOrderDetailses() {
//		return orderDetailsSet;
//	}
//
//	public void setOrderDetailses(Set<OrderDetails> orderDetailses) {
//		this.orderDetailsSet = orderDetailses;
//	}
	
    @FXML
    private void handleAdd() {
		int selectedIndex = addProductToOrderTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			ProductModel selectedItem = addProductToOrderTable.getSelectionModel().getSelectedItem();
			Integer id = selectedItem.getProductId();
			Products tempProduct = ProductsQueries.getProducts(id);
			order = OrderEditDialogController.getOrder();
			getOrderDetails().setOrders(order);
			getOrderDetails().setProducts(tempProduct);
			getOrderDetails().setUnitPrice(tempProduct.getUnitPrice());
			getOrderDetails().setQuantity(Short.parseShort(quantityField.getText()));
			getOrderDetails().setDiscount(Float.parseFloat(discountField.getText()));
			orderDetailsList.add(getOrderDetails());	
//			orderDetailsSet = order.getOrderDetailses();
//			orderDetailsSet.add(getOrderDetails());
//			order.setOrderDetailses(orderDetailsSet);
//			orderEditDialogController.setOrder2(order);
//			orderDetailsList = mainAppGui.getOrderDetailsList();
//			orderDetailsList.add(getOrderDetails());
//			mainAppGui.setOrderDetailsList(orderDetailsList);
			okClicked = true;
			dialogStage.close();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
//			alert.initOwner(addProductToOrderTable.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

//	@FXML
//	private void handleAdd() {
//		System.out.println("cos");
//	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public boolean isOkClicked() {
		return okClicked;
	}

}
