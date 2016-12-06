package info.projekt.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import info.projekt.gui.MainAppGUI;
import info.projekt.gui.model.ProductModel;


public class ProductOverviewController {
    @FXML
    private TableView<ProductModel> productTable;
    @FXML
    private TableColumn<ProductModel, Integer> productIdColumn;
    @FXML
    private TableColumn<ProductModel, String> productNameColumn;
    @FXML
    private TableColumn<ProductModel, Object> supplierIdColumn;
    @FXML
    private TableColumn<ProductModel, Object> categoryIdColumn;
    @FXML
    private TableColumn<ProductModel, String> quantityPerUnitColumn;
    @FXML
    private TableColumn<ProductModel, Double> unitPriceColumn;
    @FXML
    private TableColumn<ProductModel, Integer> unitsInStockColumn;
    @FXML
    private TableColumn<ProductModel, Integer> unitsOnOrderColumn;
    @FXML
    private TableColumn<ProductModel, Integer> reorderLevelColumn;
    @FXML
    private TableColumn<ProductModel, String> discontinuedColumn;
    
    @FXML
    private Label productIdLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label supplierIdLabel;
    @FXML
    private Label categoryIdLabel;
    @FXML
    private Label quantityPerUnitLabel;
    @FXML
    private Label unitPriceLabel;
    @FXML
    private Label unitsInStockLabel;
    @FXML
    private Label unitsOnOrderLabel;
    @FXML
    private Label reorderLevelLabel;
    @FXML
    private Label discontinuedLabel;

    
    // Reference to the main application.
    private MainAppGUI mainAppGUI;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ProductOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        productIdColumn.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        quantityPerUnitColumn.setCellValueFactory(cellData -> cellData.getValue().quantityPerUnitProperty());
        unitPriceColumn.setCellValueFactory(cellData -> cellData.getValue().unitPriceProperty().asObject());
        unitsInStockColumn.setCellValueFactory(cellData -> cellData.getValue().unitsInStockProperty().asObject());
        unitsOnOrderColumn.setCellValueFactory(cellData -> cellData.getValue().unitsOnOrderProperty().asObject());
        reorderLevelColumn.setCellValueFactory(cellData -> cellData.getValue().reorderLevelProperty().asObject());
        discontinuedColumn.setCellValueFactory(cellData -> cellData.getValue().discontinuedProperty());
        
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainAppGUI(MainAppGUI mainAppGUI) {
        this.mainAppGUI = mainAppGUI;

        // Add observable list data to the table
        productTable.setItems(mainAppGUI.getProductData());
    }
}