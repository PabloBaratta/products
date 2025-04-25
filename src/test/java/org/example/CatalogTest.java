package org.example;

import org.example.products.Catalog;
import org.example.products.Category;
import org.example.products.CategorySearchCriteria;
import org.example.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {

	private Product productA;
	private Product productB;
	private Catalog catalog;

	@BeforeEach
	void setUp() {
		productA = new Product("Laptop", "P001", new Category("Electronics"));
		productB = new Product("Mouse", "P002", new Category("Electronics"));
		var map = new HashMap<Product, Double>();
		map.put(productA, 1500.0);
		catalog = new Catalog(map);
	}

	@Test
	void returnsOptionalWithCorrectPriceForExistingProduct() {
		Optional<Double> price = catalog.getPriceOf(productA);

		assertTrue(price.isPresent());
		assertEquals(1500.0, price.get());
	}

	@Test
	void returnsEmptyOptionalForProductNotInCatalog() {
		Product unknownProduct = new Product("Keyboard", "P003", new Category("Electronics"));

		Optional<Double> price = catalog.getPriceOf(unknownProduct);

		assertTrue(price.isEmpty());
	}

	@Test
	void throwsExceptionWhenProductIsNull() {
		Exception ex = assertThrows(NullPointerException.class, () -> catalog.getPriceOf(null));

		assertEquals("Product cannot be null", ex.getMessage());
	}

	@Test
	void throwsExceptionWhenCatalogIsConstructedWithNullMap() {
		Exception ex = assertThrows(NullPointerException.class, () -> new Catalog(null));

		assertEquals("Prices map cannot be null", ex.getMessage());
	}

	@Test
	void findByFindsBasedOnACategory() {

		CategorySearchCriteria electronicsSearch = new CategorySearchCriteria(new Category("Electronics"));
		assertEquals(1, catalog.findBy(electronicsSearch).size());
	}

	@Test
	void findByDoesNotReturnBasedOnACategoryWhichDoesNotHaveProducts() {
		CategorySearchCriteria fashionSearch = new CategorySearchCriteria(new Category("Fashion"));
		assertEquals(0, catalog.findBy(fashionSearch).size());
	}

	@Test
	void testAddProductWithValidProduct() {
		double price = 100.0;

		catalog.addProduct(productB, price);
		Optional<Double> actualPrice = catalog.getPriceOf(productB);
		assertTrue(actualPrice.isPresent(), "Price should be present");
		assertEquals(price, actualPrice.get(), "The price should match the given price");
	}

	@Test
	void testAddProductWithExistingProduct() {

		catalog.addProduct(productA, 150.0);
		Optional<Double> actualPrice = catalog.getPriceOf(productA);
		assertTrue(actualPrice.isPresent(), "Price should be present");
		assertEquals(150.0, actualPrice.get(), "The price should be updated");
	}
}