/*package info.projekt.gui.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import info.projekt.database.Products;
import info.projekt.gui.MainAppGUI;
//import ch.makery.address.model.Person;
//import ch.makery.address.util.DateUtil;

public class ProductOverviewController {
    @FXML
    private TableView<Products> productTable;
    @FXML
    private TableColumn<Products, String> productIDColumn;
    @FXML
    private TableColumn<Products, String> productNameColumn;
    @FXML
    private TableColumn<Products, String> supplierIDColumn;
    @FXML
    private TableColumn<Products, String> categoryIDColumn;
    @FXML
    private TableColumn<Products, String> quantityPerUnitColumn;
    @FXML
    private TableColumn<Products, String> unitPriceColumn;
    @FXML
    private TableColumn<Products, String> unitsInStockColumn;
    @FXML
    private TableColumn<Products, String> unitsOnOrderColumn;
    @FXML
    private TableColumn<Products, String> reorderLevelColumn;
    @FXML
    private TableColumn<Products, String> DiscontinuedColumn;
    
    @FXML
    private Label productIDLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label supplierIDLabel;
    @FXML
    private Label categoryIDLabel;
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
    private Label DiscontinuedLabel;

    // Reference to the main application.
    private MainAppGUI mainAppGUI;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
/*    public ProductOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
/*    @FXML
   private void initialize() {
        // Initialize the person table with the two columns.
    	productIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        productTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProductDetails(newValue));
    
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
 /*   public void setMainAppGUI(MainAppGUI mainAppGUI) {
        this.mainAppGUI = mainAppGUI;

        // Add observable list data to the table
        productTable.setItems(mainAppGUI.getProductData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
 /*   private void showProductDetails(Products product) {
        if (product != null) {
            // Fill the labels with info from the person object.
            productIDLabel.setText(product.getProductId());
            productNameLabel.setText(product.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
          
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
/*    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
 /*   @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
  /*  @FXML
   private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    /*
    private void handleOpenNewDialog() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    */
//}
