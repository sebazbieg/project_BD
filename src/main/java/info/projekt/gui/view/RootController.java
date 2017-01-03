package info.projekt.gui.view;

import info.projekt.gui.MainAppGui;
import javafx.fxml.FXML;

public class RootController {
	private MainAppGui mainAppGui;

	public RootController() {
	}

	@FXML
	private void initialize() {
		// System.out.println("Witaj w programie!");
	}

	public void setMainAppGUI(MainAppGui mainAppGUI) {
		this.mainAppGui = mainAppGUI;
	}

	//
	public MainAppGui getMainAppGUI() {
		return mainAppGui;
	}

	@FXML
	private void handleProductsClicked() {
		mainAppGui.refreshProductOverview();
		mainAppGui.showProductsOverview();
	}

	@FXML
	private void handleOrdersClicked() {
		mainAppGui.refreshOrderOverview();
		mainAppGui.showOrdersOverview();
	}
	
	@FXML
	private void handleRaportsClicked() {
		mainAppGui.showProductsRaports();
	}
	
	@FXML
	private void handleExit(){
		mainAppGui.getPrimaryStage().close();
	}


}
