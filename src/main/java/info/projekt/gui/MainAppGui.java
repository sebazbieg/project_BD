package info.projekt.gui;

import java.io.IOException;
import java.util.ArrayList;

import info.projekt.dao.OrdersQueries;
import info.projekt.dao.ProductsQueries;
import info.projekt.database.Orders;
import info.projekt.database.Products;
import info.projekt.gui.model.OrderModel;
import info.projekt.gui.model.ProductModel;
import info.projekt.gui.view.OrderEditDialogController;
import info.projekt.gui.view.OrderOverviewController;
import info.projekt.gui.view.ProductEditDialogController;
import info.projekt.gui.view.ProductOverviewController;
import info.projekt.gui.view.RootController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.lang.Integer;

public class MainAppGui extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	private ObservableList<ProductModel> productData = FXCollections.observableArrayList();
	private ArrayList<Products> productList = ProductsQueries.ProductsList();

	private ObservableList<OrderModel> orderData = FXCollections.observableArrayList();
	private ArrayList<Orders> orderList = OrdersQueries.OrdersList();

	public MainAppGui() {
		refreshProductOverview();
		refreshOrderOverview();
	}

	public ObservableList<ProductModel> getProductData() {
		return productData;
	}

	public ArrayList<Products> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Products> productList) {
		this.productList = productList;
	}

	public ObservableList<OrderModel> getOrderData() {
		return orderData;
	}

	public ArrayList<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Orders> orderList) {
		this.orderList = orderList;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Northwind");

		initRootLayout();
		// showLoginPane();
		// showProductsOverview();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			RootController controller = loader.getController();
			controller.setMainAppGUI(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showLoginPane() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/LoginPanel.fxml"));
			AnchorPane loginPanel = (AnchorPane) loader.load();

			rootLayout.setCenter(loginPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showProductsOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/ProductsOverview.fxml"));
			AnchorPane productsOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(productsOverview);

			ProductOverviewController controller = loader.getController();
			controller.setMainAppGUI(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showProductEditDialog(Products product) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/ProductEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Product");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			ProductEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProduct(product);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean showOrderEditDialog(Orders order) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/OrderEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Order");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			OrderEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setOrder(order);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showOrdersOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/OrdersOverview.fxml"));
			AnchorPane ordersOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(ordersOverview);

			OrderOverviewController controller = loader.getController();
			controller.setMainAppGUI(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void refreshOrderOverview() {
		for (int i = 0; i < orderList.size(); i++) {
			Orders tempOrder = orderList.get(i);
			orderData.add(
					new OrderModel(tempOrder.getOrderId(), null, null, null, null, null, null, tempOrder.getFreight(),
							tempOrder.getShipName(), tempOrder.getShipAddress(), tempOrder.getShipCity(),
							tempOrder.getShipRegion(), tempOrder.getShipPostalCode(), tempOrder.getShipCountry()));
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
