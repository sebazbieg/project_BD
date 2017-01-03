package info.projekt.gui.view;

import java.io.IOException;
import java.util.ArrayList;

import info.projekt.dao.CustomersQueries;
import info.projekt.dao.ProductsQueries;
import info.projekt.database.Customers;
import info.projekt.database.Products;
import info.projekt.gui.MainAppGui;
import info.projekt.gui.model.CustomerModel;
import info.projekt.gui.model.CustomersRaportModel;
import info.projekt.gui.model.ProductModel;
import info.projekt.gui.model.ProductsRaportModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductsRaportController {

	@FXML
	private TableView<ProductsRaportModel> productsRaportTable;
	@FXML
	private TableColumn<ProductsRaportModel, String> customerNameColumn;
	@FXML
	private TableColumn<ProductsRaportModel, Long> quantityColumn;
	@FXML
	private Label productLabel;

	@FXML
	private TableView<CustomersRaportModel> customerRaportTable;
	@FXML
	private TableColumn<CustomersRaportModel, Integer> orderIdColumn;
	@FXML
	private TableColumn<CustomersRaportModel, Double> totalPriceColumn;
	@FXML
	private Label customerLabel;

	private MainAppGui mainAppGui;
	private ObservableList<ProductModel> productData = FXCollections.observableArrayList();
	private ArrayList<Products> productList = ProductsQueries.ProductsList();
	private ObservableList<CustomerModel> customersData = FXCollections.observableArrayList();
	private ArrayList<Customers> customersList = CustomersQueries.customersList();

	public ProductsRaportController() {
		refreshProductOverview();
		refreshCustomerOverview();
	}

	@FXML
	private void initialize() {
		customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
		quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
		orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty().asObject());
		totalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asObject());

	}

	public void setMainAppGUI(MainAppGui mainAppGui) {
		this.mainAppGui = mainAppGui;
		productsRaportTable.setItems(mainAppGui.getProductRaportData());
		customerRaportTable.setItems(mainAppGui.getCustomerRaportData());
	}

	public ObservableList<ProductModel> getProductData() {
		return productData;
	}

	public ObservableList<CustomerModel> getCustomerData() {
		return customersData;
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

	public void refreshCustomerOverview() {
		for (int i = 0; i < customersList.size(); i++) {
			Customers tempCustomer = customersList.get(i);
			customersData.add(new CustomerModel(tempCustomer.getCompanyName()));
		}
	}

	public String showAddProductToProductsRaport() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/AddProductToProductsRaport.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Add Product to Products Raport");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			// dialogStage.initOwner(mainAppGui.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			AddProductToProductsRaportController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProductsRaportController(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.getProductName();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String showAddCustomerToProductsRaport() {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/AddCustomerToProductsRaport.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Add Customer to Products Raport");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			// dialogStage.initOwner(mainAppGui.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			AddCustomerToProductsRaportController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProductsRaportController(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.getCustomerName();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@FXML
	private void handleAddProduct() {
		productLabel.setText(showAddProductToProductsRaport());
		mainAppGui.refreshProductRaport();
	}

	@FXML
	private void handleAddCustomer() {
		customerLabel.setText(showAddCustomerToProductsRaport());
		mainAppGui.refreshCustomerRaport();
	}

}
