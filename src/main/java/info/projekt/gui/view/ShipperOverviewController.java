package info.projekt.gui.view;

import info.projekt.dao.ShippersQueries;
import info.projekt.database.Orders;
import info.projekt.database.Shippers;
import info.projekt.gui.model.ShipperModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ShipperOverviewController {
	
	@FXML
	private TableView<ShipperModel> shipperTable;
	@FXML
	private TableColumn<ShipperModel, String> shipperNameColumn;
	
	@FXML
	private Label shipperNameLabel;

	private Stage dialogStage;
	private boolean okClicked = false;
	Orders order;

	public ShipperOverviewController() {
	}

	@FXML
	private void initialize() {
		shipperNameColumn.setCellValueFactory(cellData -> cellData.getValue().shipperNameProperty());
	}
	
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
	public void setOrderEditDialogController(OrderEditDialogController orderEditDialogController) {
		shipperTable.setItems(orderEditDialogController.getShipperData());
	}
   	
    @FXML
    private void handleAdd() {
		int selectedIndex = shipperTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			ShipperModel selectedItem = shipperTable.getSelectionModel().getSelectedItem();
			String name = selectedItem.getShippperName();
			Shippers tempShipper = ShippersQueries.getShipper(name);
			order = OrderEditDialogController.getOrder();
			order.setShippers(tempShipper);
			okClicked = true;
			dialogStage.close();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
//			alert.initOwner(addProductToOrderTable.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Customer Selected");
			alert.setContentText("Please select a customer in the table.");

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
