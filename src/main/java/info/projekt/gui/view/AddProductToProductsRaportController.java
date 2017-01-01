package info.projekt.gui.view;

import info.projekt.dao.ProductsQueries;
import info.projekt.database.Products;
import info.projekt.gui.model.ProductModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddProductToProductsRaportController {
	
	@FXML
	private TableView<ProductModel> productsTable;
	@FXML
	private TableColumn<ProductModel, String> productNameColumn;

	private Stage dialogStage;
	private boolean okClicked = false;
	public static Products tempProduct;

	String productName;
	ProductsRaportController productRaportController;

	public AddProductToProductsRaportController() {
	}

	@FXML
	private void initialize() {
		productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setProductsRaportController(ProductsRaportController productsRaportController) {
		productsTable.setItems(productsRaportController.getProductData());
	}
	
	public String getProductName() {
		return productName;
	}

	@FXML
	private void handleAdd() {
		int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			ProductModel selectedItem = productsTable.getSelectionModel().getSelectedItem();
			Integer id = selectedItem.getProductId();
			tempProduct = ProductsQueries.getProducts(id);
			productName = tempProduct.getProductName();
			okClicked = true;
			dialogStage.close();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			// alert.initOwner(addProductToOrderTable.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Shipper Selected");
			alert.setContentText("Please select a shipper in the table.");

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
