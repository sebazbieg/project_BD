package info.projekt.gui.view;

import java.io.IOException;
import java.util.ArrayList;

import info.projekt.dao.CategoriesQueries;
import info.projekt.dao.SuppliersQueries;
import info.projekt.database.Categories;
import info.projekt.database.Products;
import info.projekt.database.Suppliers;
import info.projekt.gui.MainAppGui;
import info.projekt.gui.model.CategoryModel;
import info.projekt.gui.model.SupplierModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
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
	    
	    @FXML
	    private Label categoryLabel;
	    @FXML
	    private Label supplierLabel;

		private ObservableList<CategoryModel> categoryData = FXCollections.observableArrayList();
		private ArrayList<Categories> categoryList = CategoriesQueries.categoriesList();
		
		private ObservableList<SupplierModel> supplierData = FXCollections.observableArrayList();
		private ArrayList<Suppliers> supplierList = SuppliersQueries.suppliersList();
	    
	    private Stage dialogStage;
	    private static Products product;
	    private boolean okClicked = false;
	//    private MainAppGui mainAppGui;

		/**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     * 
	     */
	    
	    public ProductEditDialogController() {
	    	refreshCategoryOverview();
	    	refreshSupplierOverview();
		}
	   

		@FXML
	    private void initialize() {
	    }
		
		public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }


	    public static Products getProduct() {
			return product;
		}
	    
	    public void setProduct(Products product) {
	        ProductEditDialogController.product = product;

	        productNameField.setText(product.getProductName());
	        quantityPerUnitField.setText(product.getQuantityPerUnit());
	        unitPriceField.setText(String.valueOf(product.getUnitPrice()));
	        unitsInStockField.setText(Integer.toString(product.getUnitsInStock()));
	        unitsOnOrderField.setText(Integer.toString(product.getUnitsOnOrder()));
	        reorderLevelField.setText(Integer.toString(product.getReorderLevel()));
	        discontinuedField.setText(product.getDiscontinued());
	    }
	    
//		public void setMainAppGui(MainAppGui mainAppGui) {
//			this.mainAppGui = mainAppGui;
//		}
		
		public void setProduct2(Products product) {
			ProductEditDialogController.product = product;
		}
		
		public ObservableList<CategoryModel> getCategoryData() {
			return categoryData;
		}
		
		public ObservableList<SupplierModel> getSupplierData() {
			return supplierData;
		}
			    
	    private void refreshCategoryOverview() {
			for (int i = 0; i < categoryList.size(); i++) {
				Categories tempCategory = categoryList.get(i);
				categoryData.add(new CategoryModel(tempCategory.getCategoryName()));
			}	// TODO Auto-generated method stub
			
		}
	    private void refreshSupplierOverview() {
			for (int i = 0; i < supplierList.size(); i++) {
				Suppliers tempSupplier = supplierList.get(i);
				supplierData.add(new SupplierModel(tempSupplier.getCompanyName()));
			}	// TODO Auto-generated method stub
			
		}

	    public boolean isOkClicked() {
	        return okClicked;
	    }
	    
	    public String showAddCategoryToProductDialog() {
			try {
				// Load the fxml file and create a new stage for the popup dialog.
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainAppGui.class.getResource("view/CategoryOverview.fxml"));
				AnchorPane page = (AnchorPane) loader.load();
				// Create the dialog Stage.
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Add Category to Product");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				// dialogStage.initOwner(mainAppGui.getPrimaryStage());
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);

				// Set the person into the controller.
				CategoryOverviewController controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setProductEditDialogController(this);

				// Show the dialog and wait until the user closes it
				dialogStage.showAndWait();

				return controller.getCategoryName();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
	    
	    public String showAddSupplierToProductDialog() {
			try {
				// Load the fxml file and create a new stage for the popup dialog.
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainAppGui.class.getResource("view/SupplierOverview.fxml"));
				AnchorPane page = (AnchorPane) loader.load();
				// Create the dialog Stage.
				Stage dialogStage = new Stage();
				dialogStage.setTitle("Add Supplier to Product");
				dialogStage.initModality(Modality.WINDOW_MODAL);
				// dialogStage.initOwner(mainAppGui.getPrimaryStage());
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);

				// Set the person into the controller.
				SupplierOverviewController controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setProductEditDialogController(this);

				// Show the dialog and wait until the user closes it
				dialogStage.showAndWait();

				return controller.getSupplierName();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
	    
		@FXML
		private void handleAddCategory() {
			categoryLabel.setText(showAddCategoryToProductDialog());
		}
		
		@FXML
		private void handleAddSupplier() {
			supplierLabel.setText(showAddSupplierToProductDialog());
		}

	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	        	getProduct().setProductName(productNameField.getText());
	        	getProduct().setQuantityPerUnit(quantityPerUnitField.getText());
	        	getProduct().setUnitPrice(Double.parseDouble(unitPriceField.getText()));
	        	getProduct().setUnitsInStock(Short.parseShort(unitsInStockField.getText()));
	        	getProduct().setUnitsOnOrder(Short.parseShort(unitsOnOrderField.getText()));
	        	getProduct().setReorderLevel(Short.parseShort(reorderLevelField.getText()));
	        	getProduct().setDiscontinued(discontinuedField.getText());		

	            okClicked = true;
	            dialogStage.close();
	        }
	    }


	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

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
	                errorMessage += "Podaj cenę w formacie zł.gr!\n"; 
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
