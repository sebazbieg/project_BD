package info.projekt.gui.view;

import java.util.ArrayList;

import info.projekt.dao.OrdersQueries;
import info.projekt.dao.ProductsQueries;
import info.projekt.database.OrderDetails;
import info.projekt.database.Orders;
import info.projekt.database.Products;
import info.projekt.gui.MainAppGui;
import info.projekt.gui.model.OrderModel;
import info.projekt.gui.model.ProductModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	
	private OrderEditDialogController orderEditDialogController;
	
	public AddProductToOrderDialogController() {
	}
	
    private Stage dialogStage;
    private static OrderDetails orderDetails;
    private boolean okClicked = false;
	
	@FXML
	private void initialize() {
		addProductToOrderIdColumn.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
		addProductToOrderNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
		addProductToOrderPriceColumn.setCellValueFactory(cellData -> cellData.getValue().unitPriceProperty().asObject());		
	}
	
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public static OrderDetails getOrderDetails() {
		return orderDetails;
	}
    
	public void setOrderEditDialogController(OrderEditDialogController orderEditDialogController) {
		this.orderEditDialogController = orderEditDialogController;
		addProductToOrderTable.setItems(orderEditDialogController.getProductData());
	}
    
    public void setOrderDetails(OrderDetails orderDetails) {
    	AddProductToOrderDialogController.orderDetails = orderDetails;
    	
    	quantityField.setText(Short.toString(orderDetails.getQuantity()));
    	discountField.setText(Float.toString(orderDetails.getDiscount()));
    }
	

	@FXML
	private void handleAdd() {
		System.out.println("cos");
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	public boolean isOkClicked() {
		return okClicked;
	}

}
