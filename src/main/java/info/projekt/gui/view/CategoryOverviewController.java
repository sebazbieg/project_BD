package info.projekt.gui.view;

import info.projekt.dao.CategoriesQueries;
import info.projekt.database.Categories;
import info.projekt.database.Products;
import info.projekt.gui.model.CategoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CategoryOverviewController {

	@FXML
	private TableView<CategoryModel> categoryTable;
	@FXML
	private TableColumn<CategoryModel, String> categoryNameColumn;

	private Stage dialogStage;
	private boolean okClicked = false;
	private Products product;
	private String categoryName;

	public CategoryOverviewController() {
	}

	@FXML
	private void initialize() {
		categoryNameColumn.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setProductEditDialogController(ProductEditDialogController productEditDialogController) {
		categoryTable.setItems(productEditDialogController.getCategoryData());
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	

	@FXML
	private void handleAdd() {
		int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			CategoryModel selectedItem = categoryTable.getSelectionModel().getSelectedItem();
			String name = selectedItem.getCategoryName();
			Categories tempCategory = CategoriesQueries.getCategory(name);
			product = ProductEditDialogController.getProduct();
			product.setCategories(tempCategory);
			categoryName = tempCategory.getCategoryName();
			okClicked = true;
			dialogStage.close();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			// alert.initOwner(addProductToOrderTable.getPrimaryStage());
			alert.setTitle("No Selection");
			alert.setHeaderText("No Category Selected");
			alert.setContentText("Please select a category in the table.");

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