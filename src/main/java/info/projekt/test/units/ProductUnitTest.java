package info.projekt.test.units;

import org.junit.Test;

import info.projekt.dao.ProductsQueries;
import info.projekt.database.Products;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ProductUnitTest {
	// Tworzymy obiekt products do testów
	Products testProduct;
	Integer result;

	@Test
	public void AddAndGetProductTest() {
		testProduct = ProductsQueries.getProducts(1);
		testProduct.setProductName("BigMilk");
		result = ProductsQueries.addProducts(testProduct);
		testProduct = ProductsQueries.getProducts(result);
		Assert.assertEquals(result, testProduct.getProductId());
		Assert.assertEquals("BigMilk", testProduct.getProductName());
		ProductsQueries.deleteProducts(result);
	}

	@Test
	public void updateProductTest() {
		testProduct = ProductsQueries.getProducts(1);
		testProduct.setUnitsInStock((short) 10);
		testProduct.setUnitsOnOrder((short) 0);
		result = ProductsQueries.addProducts(testProduct);
		ProductsQueries.updateProducts(result, (short) 3);
		testProduct = ProductsQueries.getProducts(result);
		System.out.println(testProduct.getUnitsInStock());
		Assert.assertEquals(7, testProduct.getUnitsInStock());
		Assert.assertEquals(3, testProduct.getUnitsOnOrder());
		ProductsQueries.deleteProducts(result);
	}

}
