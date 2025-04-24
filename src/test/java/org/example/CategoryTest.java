package org.example;

import org.example.products.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

	@Test
	void storesNameCorrectly() {
		Category category = new Category("Books");
		assertEquals("Books", category.name());
	}

	@Test
	void categoriesWithSameNameAreEqual() {
		Category c1 = new Category("Books");
		Category c2 = new Category("Books");
		assertEquals(c1, c2);
	}

	@Test
	void categoriesWithSameNameButDifferentCaseAreEqual() {
		Category c1 = new Category("books");
		Category c2 = new Category("BOOKS");
		assertEquals(c1, c2);
	}

	@Test
	void categoriesWithDifferentNamesAreNotEqual() {
		Category c1 = new Category("Books");
		Category c2 = new Category("Music");
		assertNotEquals(c1, c2);
	}
}