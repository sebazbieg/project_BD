package info.projekt.gui.view;

import java.util.ArrayList;

import info.projekt.dao.CategoriesQueries;
import info.projekt.dao.SuppliersQueries;
import info.projekt.database.Categories;
import info.projekt.database.Products;
import info.projekt.database.Suppliers;
import info.projekt.gui.model.ProductModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ProductEditDialogController {

	 @FXML
	    private TextField productNameField;
	    @FXML
	    private TextField quantityPerUnitField;
	    @FXML
	    private TextField unitPriceField;
	    @FXML
	    private TextField unitsInStockField;
	    @FXML
	    private TextField unitsOnOrderField;
	    @FXML
	    private TextField reorderLevelField;
	    @FXML
	    private TextField discontinuedField;


	    private Stage dialogStage;
	    public static Products productModel;
	    private boolean okClicked = false;

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    }

	    /**
	     * Sets the stage of this dialog.
	     * 
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    /**
	     * Sets the person to be edited in the dialog.
	     * 
	     * @param person
	     */
	    public void setProduct(Products productModel) {
	        this.productModel = productModel;

	        productNameField.setText(productModel.getProductName());
	        quantityPerUnitField.setText(productModel.getQuantityPerUnit());
	        unitPriceField.setText(String.valueOf(productModel.getUnitPrice()));
	        unitsInStockField.setText(Integer.toString(productModel.getUnitsInStock()));
	        unitsOnOrderField.setText(Integer.toString(productModel.getUnitsOnOrder()));
	        reorderLevelField.setText(Integer.toString(productModel.getReorderLevel()));
	        discontinuedField.setText(productModel.getDiscontinued());
	    }

	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     * 
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	        	productModel.setProductName(productNameField.getText());
	        	productModel.setQuantityPerUnit(quantityPerUnitField.getText());
	        	productModel.setUnitPrice(Double.parseDouble(unitPriceField.getText()));
	        	productModel.setUnitsInStock(Short.parseShort(unitsInStockField.getText()));
	        	productModel.setUnitsOnOrder(Short.parseShort(unitsOnOrderField.getText()));
	        	productModel.setReorderLevel(Short.parseShort(reorderLevelField.getText()));
	        	productModel.setDiscontinued(discontinuedField.getText());
	    		Suppliers suppliers = SuppliersQueries.getSupplier();
	    		//Categories categories = CategoriesQueries.getCustomer();
	    		ArrayList list = (ArrayList) CategoriesQueries.categoriesList();
	    		productModel.setCategories((Categories) list.get(1));
	    		productModel.setSuppliers(suppliers);

	            okClicked = true;
	            dialogStage.close();
	        }
	    }

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	    /**
	     * Validates the user input in the text fields.
	     * 
	     * @return true if the input is valid
	     */
	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (productNameField.getText() == null || productNameField.getText().length() == 0) {
	            errorMessage += "Podaj nazwę poroduktu!\n"; 
	        }
	        if (quantityPerUnitField.getText() == null || quantityPerUnitField.getText().length() == 0) {
	            errorMessage += "Podaj jednostkę!\n"; 
	        }
	        if (unitPriceField.getText() == null || unitPriceField.getText().length() == 0) {
	            errorMessage += "Podaj cenę jednostkową!\n"; 
	        } else {
	            try {
	                Double.parseDouble(unitPriceField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "Podaj cenę w formacje zł.gr!\n"; 
	            }
	        }
	        if (unitsInStockField.getText() == null || unitsInStockField.getText().length() == 0) {
	            errorMessage += "Podaj stan magazynu!\n"; 
	        } else {
	            try {
	                Integer.parseInt(unitsInStockField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "Podaj liczbę całkowitą!\n"; 
	            }
	        }
	        if (unitsOnOrderField.getText() == null || unitsOnOrderField.getText().length() == 0) {
	            errorMessage += "Podaj stan zamówienia!\n"; 
	        } else {
	            try {
	                Integer.parseInt(unitsOnOrderField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "Podaj liczbę całkowitą!\n"; 
	            }
	        }
	        if (reorderLevelField.getText() == null || reorderLevelField.getText().length() == 0) {
	            errorMessage += "Podaj poziom zamawiania!\n"; 
	        } else {
	            try {
	                Integer.parseInt(reorderLevelField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "Podaj liczbę całkowitą!\n"; 
	            }
	        }
	        if (discontinuedField.getText() == null || discontinuedField.getText().length() == 0) {
	            errorMessage += "Podaj czy produkt jest wycofany ze sprzedaży!\n"; 
	        }


	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Please correct invalid fields");
	            alert.setContentText(errorMessage);

	            alert.showAndWait();

	            return false;
	        }
	    }

}
