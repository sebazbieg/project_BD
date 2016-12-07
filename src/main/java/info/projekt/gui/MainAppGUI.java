package info.projekt.gui;

import java.io.IOException;
import java.util.ArrayList;

import info.projekt.dao.ProductsQueries;
import info.projekt.database.Products;
import info.projekt.gui.model.ProductModel;
import info.projekt.gui.view.ProductOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.lang.Integer;

public class MainAppGUI extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private ObservableList<ProductModel> productData = FXCollections.observableArrayList();
	private ArrayList<Products> productList = ProductsQueries.ProductsList();
	
	

	public MainAppGUI() {
		for (int i = 0; i < productList.size(); i++) {
			Products tempProduct = productList.get(i);
			productData.add(new ProductModel(tempProduct.getProductId(), tempProduct.getProductName(), null, null,
					tempProduct.getQuantityPerUnit(), tempProduct.getUnitPrice(),
					Integer.valueOf(tempProduct.getUnitsInStock()), Integer.valueOf(tempProduct.getUnitsOnOrder()),
					Integer.valueOf(tempProduct.getReorderLevel()), tempProduct.getDiscontinued()));
		}
	}

	public ObservableList<ProductModel> getProductData() {
		return productData;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Northwind");

		initRootLayout();
		// showLoginPane();
		showProductsOverview();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGUI.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showLoginPane() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGUI.class.getResource("view/LoginPanel.fxml"));
			AnchorPane loginPanel = (AnchorPane) loader.load();

			rootLayout.setCenter(loginPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showProductsOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGUI.class.getResource("view/ProductsOverview.fxml"));
			AnchorPane productsOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(productsOverview);

			ProductOverviewController controller = loader.getController();
			controller.setMainAppGUI(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
