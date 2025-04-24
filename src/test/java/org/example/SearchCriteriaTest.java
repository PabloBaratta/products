package org.example;

import org.example.products.Category;
import org.example.products.CategorySearchCriteria;
import org.example.products.NameContainsSearchCriteria;
import org.example.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchCriteriaTest {

	private Product laptop;
	private Product mouse;
	private Product shoes;

	@BeforeEach
	void setUp() {
		var electronics = new Category("Electronics");
		var fashion = new Category("Fashion");

		laptop = new Product("Laptop", "P001", electronics);
		mouse = new Product("Wireless Mouse", "P002", electronics);
		shoes = new Product("Running Shoes", "P003", fashion);
	}

	@Test
	void categorySearchCriteriaMatchesCorrectCategory() {
		CategorySearchCriteria electronicsSearch = new CategorySearchCriteria(new Category("Electronics"));

		assertTrue(electronicsSearch.matches(laptop));
		assertTrue(electronicsSearch.matches(mouse));
		assertFalse(electronicsSearch.matches(shoes));
	}

	@Test
	void nameContainsSearchCriteriaMatchesSubstringIgnoreCase() {
		NameContainsSearchCriteria containsLap = new NameContainsSearchCriteria("lap");

		assertTrue(containsLap.matches(laptop));
		assertFalse(containsLap.matches(mouse));
	}

	@Test
	void nameContainsSearchCriteriaDoesNotMatchWhenSubstringMissing() {
		NameContainsSearchCriteria criteria = new NameContainsSearchCriteria("keyboard");

		assertFalse(criteria.matches(mouse));
		assertFalse(criteria.matches(laptop));
	}

}
