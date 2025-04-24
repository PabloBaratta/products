package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

	private Category category;
	private Product product;

	@BeforeEach
	void setUp() {
		category = new Category("Electronics");
		product = new Product("Laptop", "P001", category);
	}

	@Test
	void productStoresNameCorrectly() {
		assertEquals("Laptop", product.getName());
	}

	@Test
	void productStoresCodeCorrectly() {
		assertEquals("P001", product.getCode());
	}

	@Test
	void productStoresCategoryCorrectly() {
		assertEquals(category, product.getCategory());
	}

	@Test
	void productsWithSameCodeAreEqual() {
		Product sameCode = new Product("Tablet", "P001", new Category("Tablets"));
		assertEquals(product, sameCode);
	}

	@Test
	void productsWithDifferentCodesAreNotEqual() {
		Product differentCode = new Product("Laptop", "P999", category);
		assertNotEquals(product, differentCode);
	}

	@Test
	void equalProductsHaveSameHashCode() {
		Product sameCode = new Product("Other", "P001", new Category("Other"));
		assertEquals(product.hashCode(), sameCode.hashCode());
	}

}