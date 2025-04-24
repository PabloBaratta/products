package org.example.sales;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

	@Test
	void testSellerCreation() {
		Seller seller = new Seller("001", "John Doe");

		assertNotNull(seller);
		assertEquals("001", seller.getCode());
		assertEquals("John Doe", seller.getName());
	}

	@Test
	void testGetCode() {
		Seller seller = new Seller("001", "John Doe");

		assertEquals("001", seller.getCode());
	}

	@Test
	void testGetName() {
		Seller seller = new Seller("001", "John Doe");

		assertEquals("John Doe", seller.getName());
	}
}
