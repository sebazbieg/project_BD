package info.projekt.gui;

import java.io.IOException;

//import info.projekt.database.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAppGUI extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Northwind");
		
		initRootLayout();
	//	showLoginPane();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		
	}
}
