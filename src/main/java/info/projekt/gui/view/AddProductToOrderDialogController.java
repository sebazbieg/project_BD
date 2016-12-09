package info.projekt.gui.view;

import info.projekt.database.Products;
import info.projekt.gui.MainAppGui;
import info.projekt.gui.model.ProductModel;
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
	
	private MainAppGui mainAppGui;
	
	public AddProductToOrderDialogController() {
	}
	
    private Stage dialogStage;
    private static Products productToOrder;
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
    
    public static Products getProductToOrder() {
		return productToOrder;
	}
//    
//    public void setProduct(Products productToOrder) {
//        ProductEditDialogController.productToOrder = productToOrder;
//
//        quantityField.setText(productToOrder.getQuantityPerUnit());
//        unitPriceField.setText(String.valueOf(productToOrder.getUnitPrice()));
//        unitsInStockField.setText(Integer.toString(productToOrder.getUnitsInStock()));
//        unitsOnOrderField.setText(Integer.toString(productToOrder.getUnitsOnOrder()));
//        reorderLevelField.setText(Integer.toString(productToOrder.getReorderLevel()));
//        discontinuedField.setText(productToOrder.getDiscontinued());
//    }
//	
	public void setMainAppGUI(MainAppGui mainAppGui) {
		this.mainAppGui = mainAppGui;
		addProductToOrderTable.setItems(mainAppGui.getProductData());
	}
	
	@FXML
	private void handleAdd() {
		
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	
	
	
	


}
