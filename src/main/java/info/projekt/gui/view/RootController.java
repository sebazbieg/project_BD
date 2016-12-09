package info.projekt.gui.view;

import info.projekt.gui.MainAppGUI;
import javafx.fxml.FXML;


public class RootController {
	private MainAppGUI mainAppGUI;
	
	public RootController() {
	}
	
	@FXML
	private void initialize() {
		System.out.println("Witaj w programie!");
	}
	
	public void setMainAppGUI(MainAppGUI mainAppGUI) {
		this.mainAppGUI = mainAppGUI;
	}
	//
	public MainAppGUI getMainAppGUI() {
		return mainAppGUI;
	}
	
	@FXML
	private void handleProductsClicked() {
		mainAppGUI.showProductsOverview();

	}
	
	@FXML
	private void handleOrdersClicked() {
		mainAppGUI.showOrdersOverview();
	}
	
}
