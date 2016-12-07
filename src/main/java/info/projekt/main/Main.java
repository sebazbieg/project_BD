package info.projekt.main;

import java.util.ArrayList;

import info.projekt.dao.CategoriesQueries;
import info.projekt.dao.ProductsQueries;
import info.projekt.dao.SuppliersQueries;
import info.projekt.database.Categories;
import info.projekt.database.Products;
import info.projekt.database.Suppliers;

public class Main {

	public static void main(String[] args) {
		Suppliers suppliers = SuppliersQueries.getSupplier();
		//Categories categories = CategoriesQueries.getCustomer();
		ArrayList list = (ArrayList) CategoriesQueries.categoriesList();
		Products products = new Products((Categories) list.get(1), suppliers,"1","1.0",1.0,(short) 1,(short) 1,(short) 1,"y");
		System.out.println(products.getProductName());
		System.out.println(products.getProductId());
		ProductsQueries.addProducts(products);
		ProductsQueries.deleteProducts(products.getProductName());
	}

}