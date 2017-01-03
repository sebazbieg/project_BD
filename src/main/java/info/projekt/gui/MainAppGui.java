package info.projekt.gui;

import java.io.IOException;
import java.util.ArrayList;

import info.projekt.dao.CategoriesQueries;
import info.projekt.dao.CustomersQueries;
import info.projekt.dao.EmployeesQueries;
import info.projekt.dao.OrdersQueries;
import info.projekt.dao.ProductsQueries;
import info.projekt.dao.RaportsQueries;
import info.projekt.dao.ShippersQueries;
import info.projekt.dao.SuppliersQueries;
import info.projekt.database.Categories;
import info.projekt.database.Customers;
import info.projekt.database.Employees;
import info.projekt.database.OrderDetails;
import info.projekt.database.Orders;
import info.projekt.database.Products;
import info.projekt.database.Shippers;
import info.projekt.database.Suppliers;
import info.projekt.gui.model.CustomersRaportModel;
import info.projekt.gui.model.OrderDetailsModel;
import info.projekt.gui.model.OrderModel;
import info.projekt.gui.model.ProductModel;
import info.projekt.gui.model.ProductsRaportModel;
import info.projekt.gui.view.AddCustomerToProductsRaportController;
import info.projekt.gui.view.AddProductToProductsRaportController;
import info.projekt.gui.view.OrderEditDialogController;
import info.projekt.gui.view.OrderOverviewController;
import info.projekt.gui.view.ProductEditDialogController;
import info.projekt.gui.view.ProductOverviewController;
import info.projekt.gui.view.ProductsRaportController;
import info.projekt.gui.view.RootController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.lang.Integer;

public class MainAppGui extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
//	Products tempProduct;

	private ObservableList<ProductModel> productData = FXCollections.observableArrayList();
	private ArrayList<Products> productList = ProductsQueries.ProductsList();

	private ObservableList<OrderModel> orderData = FXCollections.observableArrayList();
	private ArrayList<Orders> orderList = OrdersQueries.OrdersList();

	private ObservableList<OrderDetailsModel> orderDetailsData = FXCollections.observableArrayList();
	private ArrayList<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();

	private ObservableList<ProductsRaportModel> productRaportData = FXCollections.observableArrayList();
	private ArrayList<Object[]> productRaportList;

	private ObservableList<CustomersRaportModel> customerRaportData = FXCollections.observableArrayList();
	private ArrayList<Object[]> customerRaportList;

	public MainAppGui() {
		refreshProductOverview();
		refreshOrderOverview();
		refreshOrderDetails();
	}

	public ObservableList<ProductModel> getProductData() {
		return productData;
	}

	public ArrayList<Products> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Products> productList) {
		this.productList = productList;
	}

	public ObservableList<OrderModel> getOrderData() {
		return orderData;
	}

	public ArrayList<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Orders> orderList) {
		this.orderList = orderList;
	}

	public ObservableList<OrderDetailsModel> getOrderDetailsData() {
		return orderDetailsData;
	}

	public ArrayList<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(ArrayList<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	public void getOrderDetailsDataClear() {
		orderDetailsData.clear();
	}

	public ArrayList<Object[]> getProductRaportList() {
		return productRaportList;
	}
	
	public ObservableList<ProductsRaportModel> getProductRaportData() {
		return productRaportData;
	}
	
	public ArrayList<Object[]> getCustomerRaportList() {
		return customerRaportList;
	}

	public ObservableList<CustomersRaportModel> getCustomerRaportData() {
		return customerRaportData;
	}

//	public Products getTempProduct() {
//		return tempProduct;
//	}
//
//	public void setTempProduct(Products tempProduct) {
//		this.tempProduct = tempProduct;
//	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Projekt_BD");

		initRootLayout();
		// showLoginPane();
		// showProductsOverview();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			RootController controller = loader.getController();
			controller.setMainAppGUI(this);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showLoginPane() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/LoginPanel.fxml"));
			AnchorPane loginPanel = (AnchorPane) loader.load();

			rootLayout.setCenter(loginPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showProductsOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/ProductsOverview.fxml"));
			AnchorPane productsOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(productsOverview);

			ProductOverviewController controller = loader.getController();
			controller.setMainAppGUI(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean showProductEditDialog(Products product) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/ProductEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Product");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			ProductEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setProduct(product);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean showOrderEditDialog(Orders order) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/OrderEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Order");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			OrderEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setOrder(order);
			controller.setMainAppGui(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void showOrdersOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/OrdersOverview.fxml"));
			AnchorPane ordersOverview = (AnchorPane) loader.load();

			rootLayout.setCenter(ordersOverview);

			OrderOverviewController controller = loader.getController();
			controller.setMainAppGUI(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showProductsRaports() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainAppGui.class.getResource("view/ProductsRaport.fxml"));
			AnchorPane productsRaport = (AnchorPane) loader.load();

			rootLayout.setCenter(productsRaport);

			ProductsRaportController controller = loader.getController();
			controller.setMainAppGUI(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void refreshProductOverview() {
		for (int i = 0; i < productList.size(); i++) {
			Products tempProduct = productList.get(i);
			Suppliers tempSupplier = tempProduct.getSuppliers();
			tempSupplier = SuppliersQueries.getSuppliers(tempSupplier.getSupplierId());
			Categories tempCategory = tempProduct.getCategories();
			tempCategory = CategoriesQueries.getCategory(tempCategory.getCategoryId());
			productData.add(new ProductModel(tempProduct.getProductId(), tempProduct.getProductName(),
					tempSupplier.getCompanyName(), tempCategory.getCategoryName(), tempProduct.getQuantityPerUnit(),
					tempProduct.getUnitPrice(), Integer.valueOf(tempProduct.getUnitsInStock()),
					Integer.valueOf(tempProduct.getUnitsOnOrder()), Integer.valueOf(tempProduct.getReorderLevel()),
					tempProduct.getDiscontinued()));
		}
	}

	public void refreshOrderOverview() {
		for (int i = 0; i < orderList.size(); i++) {
			Orders tempOrder = orderList.get(i);
			Customers tempCustomer = tempOrder.getCustomers();
			tempCustomer = CustomersQueries.getCustomerById(tempCustomer.getCustomerId());
			Employees tempEmployee = tempOrder.getEmployees();
			tempEmployee = EmployeesQueries.getEmployee(tempEmployee.getEmployeeId());
			Shippers tempShipper = tempOrder.getShippers();
			tempShipper = ShippersQueries.getShipper(tempShipper.getShipperId());
			orderData.add(new OrderModel(tempOrder.getOrderId(), tempCustomer.getCompanyName(),
					tempEmployee.getLastName(), tempShipper.getCompanyName(), tempOrder.getOrderDate(),
					tempOrder.getRequiredDate(), tempOrder.getShippedDate(), tempOrder.getFreight(),
					tempOrder.getShipName(), tempOrder.getShipAddress(), tempOrder.getShipCity(),
					tempOrder.getShipRegion(), tempOrder.getShipPostalCode(), tempOrder.getShipCountry()));
		}
	}

	public void refreshOrderDetails() {
		for (int i = 0; i < orderDetailsList.size(); i++) {
			OrderDetails tempOrderDetails = orderDetailsList.get(i);
			orderDetailsData.add(new OrderDetailsModel(++i, null, tempOrderDetails.getProducts().getProductName(),
					tempOrderDetails.getUnitPrice(), tempOrderDetails.getQuantity(), tempOrderDetails.getDiscount()));
		}
	}

	public void refreshProductRaport() {
		productRaportList = RaportsQueries.customersList(AddProductToProductsRaportController.tempProduct);
		productRaportData.clear();
		for (Object[] results : productRaportList) {
			productRaportData.add(new ProductsRaportModel((String) results[0], (Long) results[1]));
		}
	}

	public void refreshCustomerRaport() {
		customerRaportList = RaportsQueries.ordersList(AddCustomerToProductsRaportController.tempCustomer);
		customerRaportData.clear();
		for (Object[] results : customerRaportList) {
			customerRaportData.add(new CustomersRaportModel((Integer) results[0], (Double) results[1]));
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
