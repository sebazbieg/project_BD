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
	//	mainAppGUI.initRootLayout();
		mainAppGUI.showProductsOverview();
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(MainAppGUI.class.getResource("view/ProductsOverview.fxml"));
//			AnchorPane productsOverview = (AnchorPane) loader.load();
//			MainAppGUI.getRootLayout().setCenter(productsOverview);
//			ProductOverviewController controller = loader.getController();
//			System.out.println("1");
//			controller.setMainAppGUI(mainAppGUI);
//			System.out.println("2");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	@FXML
	private void handleOrdersClicked() {
	//	mainAppGUI.initRootLayout();
		mainAppGUI.showOrdersOverview();
	}
	
}
