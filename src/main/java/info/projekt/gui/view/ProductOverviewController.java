package info.projekt.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import info.projekt.dao.OrderDetailsQueries;
import info.projekt.dao.ProductsQueries;
import info.projekt.database.Products;
import info.projekt.gui.MainAppGui;
import info.projekt.gui.model.ProductModel;

public class ProductOverviewController {
	@FXML
	private TableView<ProductModel> productTable;
	@FXML
	private TableColumn<ProductModel, Integer> productIdColumn;
	@FXML
	private TableColumn<ProductModel, String> productNameColumn;
	@FXML
	private TableColumn<ProductModel, String> supplierColumn;
	@FXML
	private TableColumn<ProductModel, String> categoryColumn;
	@FXML
	private TableColumn<ProductModel, String> quantityPerUnitColumn;
	@FXML
	private TableColumn<ProductModel, Double> unitPriceColumn;
	@FXML
	private TableColumn<ProductModel, Integer> unitsInStockColumn;
	@FXML
	private TableColumn<ProductModel, Integer> unitsOnOrderColumn;
	@FXML
	private TableColumn<ProductModel, Integer> reorderLevelColumn;
	@FXML
	private TableColumn<ProductModel, String> discontinuedColumn;

	@FXML
	private Label productIdLabel;
	@FXML
	private Label productNameLabel;
	@FXML
	private Label supplierIdLabel;
	@FXML
	private Label categoryIdLabel;
	@FXML
	private Label quantityPerUnitLabel;
	@FXML
	private Label unitPriceLabel;
	@FXML
	private Label unitsInStockLabel;
	@FXML
	private Label unitsOnOrderLabel;
	@FXML
	private Label reorderLevelLabel;
	@FXML
	private Label discontinuedLabel;

	// Reference to the main application.
	private MainAppGui mainAppGui;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public ProductOverviewController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		productIdColumn.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
		productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
		supplierColumn.setCellValueFactory(cellData -> cellData.getValue().supplierNameProperty());
		categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
		quantityPerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().quantityPerUnitProperty());
		unitPriceColumn.setCellValueFactory(cellData -> cellData.getValue().unitPriceProperty().asObject());
		unitsInStockColumn.setCellValueFactory(cellData -> cellData.getValue().unitsInStockProperty().asObject());
		unitsOnOrderColumn.setCellValueFactory(cellData -> cellData.getValue().unitsOnOrderProperty().asObject());
		reorderLevelColumn.setCellValueFactory(cellData -> cellData.getValue().reorderLevelProperty().asObject());
		discontinuedColumn.setCellValueFactory(cellData -> cellData.getValue().discontinuedProperty());
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainAppGUI(MainAppGui mainAppGui) {
		this.mainAppGui = mainAppGui;

		// Add observable list data to the table
		productTable.setItems(mainAppGui.getProductData());
	}

	@FXML
	private void handleDeleteProduct() {
		int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			ProductModel selectedItem = productTable.getSelectionModel().getSelectedItem();
			Integer id = selectedItem.getProductId();

			if ((OrderDetailsQueries.getOrderDetailsListWithProducts(id)).isEmpty()) {
				ProductsQueries.deleteProducts(id);
				productTable.getItems().remove(selectedIndex);
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(mainAppGui.getPrimaryStage());
				alert.setTitle("Nie można usunąć produktu!");
				alert.setHeaderText("Wybrany produkt jest używany w zamówieniach");
				alert.setContentText("Najpierw usuń zamówienia zawierające ten produkt!");

				alert.showAndWait();
			}
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
	private void handleNewProduct() {
		Products tempProduct = new Products();

		boolean okClicked = mainAppGui.showProductEditDialog(tempProduct);
		if (okClicked) {
			mainAppGui.getProductData().removeAll(mainAppGui.getProductData());
			ProductsQueries.addProducts(ProductEditDialogController.getProduct());
			mainAppGui.setProductList(ProductsQueries.ProductsList());
			mainAppGui.refreshProductOverview();
		}

	}

}