package org.example.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SellerSalariesTest {

	private SellerSalaries sellerSalaries;
	private Seller seller;

	@BeforeEach
	void setUp() {
		sellerSalaries = new SellerSalaries();
		seller = new Seller("001", "Juan");
	}

	@Test
	void testRegisterSellerSalary() {
		sellerSalaries.register(seller, 2000.0);
		Optional<Double> salary = sellerSalaries.getSalaryByCode(seller);
		assertTrue(salary.isPresent(), "Salary should be present");
		assertEquals(2000.0, salary.get(), "Salary should match the registered amount");
	}

	@Test
	void testGetSalaryByCodeWhenSellerNotRegistered() {
		Optional<Double> salary = sellerSalaries.getSalaryByCode(seller);
		assertFalse(salary.isPresent(), "Salary should be absent for unregistered seller");
	}

	@Test
	void testGetSalaryByCodeWithNullSeller() {
		assertThrows(NullPointerException.class, () -> sellerSalaries.getSalaryByCode(null), "Seller cannot be null");
	}

	@Test
	void testRegisterWithNullSeller() {
		assertThrows(NullPointerException.class, () -> sellerSalaries.register(null, 2000.0), "Seller cannot be null");
	}

	@Test
	void testOverwriteSalary() {
		sellerSalaries.register(seller, 2000.0);
		sellerSalaries.register(seller, 2500.0);
		Optional<Double> salary = sellerSalaries.getSalaryByCode(seller);
		assertTrue(salary.isPresent(), "Salary should be present after overwrite");
		assertEquals(2500.0, salary.get(), "Salary should be the new overwritten amount");
	}
}
