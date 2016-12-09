package info.projekt.gui.view;

import info.projekt.gui.MainAppGui;
import info.projekt.gui.model.ProductModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddProductToOrderDialogController {

	@FXML
	private TableView<ProductModel> addProductToOrderTable;
	@FXML
	private TableColumn<ProductModel, Integer> addProductToOrderIdColumn;
	@FXML
	private TableColumn<ProductModel, String> addProductToOrderNameColumn;
	@FXML
	private TableColumn<ProductModel, Double> addProductToOrderPriceColumn;
	
	private MainAppGui mainAppGui;
	
	public AddProductToOrderDialogController() {
	}
	
	@FXML
	private void initialize() {
		
	}
	
	
	


}
