package org.example.sales;

import org.example.IdProvider;
import org.example.products.Category;
import org.example.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SellerRegisterTest {

	private SellerRegister sellerRegister;

	private SellerDto sellerDto;
	private Seller seller;
	private Sale sale;

	@BeforeEach
	void setUp() {

		var commissionRules = new BasicCommissionRules();
		var id = new IdProvider() {
			@Override
			public String provide() {
				return "001";
			}
		};

		sellerRegister = new SellerRegister(id, commissionRules);

		sellerDto = new SellerDto("Juan", 15);
		seller = new Seller("001", "Juan");
		var product = new Product("Laptop", "P001", new Category("Electronics"));
		sale = new Sale(seller, product, 100);
	}

	@Test
	void testRegisterSeller() {
		sellerRegister.register(sellerDto, 2000.0);
		Optional<Double> salary = sellerRegister.getSalaryByCode(seller);
		assertTrue(salary.isPresent(), "Salary should be present");
		assertEquals(2000.0, salary.get(), "The salary should match the expected value");
	}

	@Test
	void testRegisterSale() {
		sellerRegister.register(sellerDto, 2000.0);
		sellerRegister.registerSale(seller, sale);
		double commission = sellerRegister.calculateCommissions(seller);
		assertEquals(5.0, commission, "The commission should be 5% of 100");
	}

	@Test
	void testCalculateCommissions() {
		sellerRegister.register(sellerDto, 2000.0);
		sellerRegister.registerSale(seller, sale);
		double commission = sellerRegister.calculateCommissions(seller);
		assertEquals(5.0, commission, "The commission should be 5% of the sale");
	}

	@Test
	void testGetSalaryByCode() {
		sellerRegister.register(sellerDto, 2000.0);

		Optional<Double> salary = sellerRegister.getSalaryByCode(seller);

		assertTrue(salary.isPresent(), "Salary should be present");
		assertEquals(2000.0, salary.get(), "The salary should match the expected value");
	}

	@Test
	void testGetSalaryByCodeWhenNotRegistered() {
		Optional<Double> salary = sellerRegister.getSalaryByCode(seller);
		assertFalse(salary.isPresent(), "Salary should not be present for unregistered sellers");
	}

	@Test
	void testRegisterWithNullSeller() {
		assertThrows(NullPointerException.class, () -> sellerRegister.register(null, 2000.0), "Seller cannot be null");
	}

	@Test
	void testRegisterSaleWithNullSale() {
		assertThrows(NullPointerException.class, () -> sellerRegister.registerSale(seller, null),
				"Sale cannot be null");
	}
}