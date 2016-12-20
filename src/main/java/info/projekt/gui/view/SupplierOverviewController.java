package info.projekt.gui.view;

import info.projekt.dao.SuppliersQueries;
import info.projekt.database.Products;
import info.projekt.database.Suppliers;
import info.projekt.gui.model.SupplierModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class SupplierOverviewController {
	
	@FXML
	private TableView<SupplierModel> supplierTable;
	@FXML
	private TableColumn<SupplierModel, String> supplierNameColumn;
	
	@FXML
	private Label supplierNameLabel;

	private Stage dialogStage;
	private boolean okClicked = false;
	private Products product;
	private String supplierName;

	public SupplierOverviewController() {
	}

	@FXML
	private void initialize() {
		supplierNameColumn.setCellValueFactory(cellData -> cellData.getValue().supplierNameProperty());
	}
	
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
	public void setProductEditDialogController(ProductEditDialogController productEditDialogController) {
		supplierTable.setItems(productEditDialogController.getSupplierData());
	}
	
	public String getSupplierName() {
		return supplierName;
	}
   	
    @FXML
    private void handleAdd() {
		int selectedIndex = supplierTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			SupplierModel selectedItem = supplierTable.getSelectionModel().getSelectedItem();
			String name = selectedItem.getSupplierName();
			Suppliers tempSupplier = SuppliersQueries.getSuppliers(name);
			product = ProductEditDialogController.getProduct();
			product.setSuppliers(tempSupplier);
			supplierName = tempSupplier.getCompanyName();
			okClicked = true;
			dialogStage.close();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
//			alert.initOwner(addProductToOrderTable.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Supplier Selected");
			alert.setContentText("Please select a supplier in the table.");

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
