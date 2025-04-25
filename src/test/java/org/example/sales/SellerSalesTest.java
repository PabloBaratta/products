package org.example.sales;

import org.example.products.Category;
import org.example.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SellerSalesTest {

	private SellerSales sellerSales;
	private Seller seller;
	private Sale sale;

	@BeforeEach
	void setUp() {
		sellerSales = new SellerSales();
		seller = new Seller("001", "Juan");
		sale = new Sale(seller, new Product("Laptop", "L001", new Category("Electronics")), 500.0);
	}

	@Test
	void testRegisterSeller() {
		sellerSales.registerSeller(seller);
		assertThrows(IllegalArgumentException.class, () -> sellerSales.registerSeller(seller), "Seller already exists");
	}

	@Test
	void testRegisterSaleForNonExistingSeller() {
		assertThrows(IllegalArgumentException.class, () -> sellerSales.registerSale(seller, sale),
				"Seller does not exist in the registry");
	}

	@Test
	void testCalculateCommissionsWithoutSales() {
		sellerSales.registerSeller(seller);
		double commission = sellerSales.calculateCommissions(seller, new BasicCommissionRules());
		assertEquals(0.0, commission, "Expected commission to be 0 when no sales are registered");
	}

	@Test
	void testCalculateCommissionsForOneSale() {
		sellerSales.registerSeller(seller);
		sellerSales.registerSale(seller, sale);
		double commission = sellerSales.calculateCommissions(seller, new BasicCommissionRules());
		assertEquals(25.0, commission, "Expected commission to be 5% of 500.0");
	}

	@Test
	void testCalculateCommissionsForMultipleSales() {
		sellerSales.registerSeller(seller);
		sellerSales.registerSale(seller, sale);
		sellerSales.registerSale(seller,
				new Sale(seller, new Product("Phone", "P001", new Category("Electronics")), 200.0));

		double commission = sellerSales.calculateCommissions(seller, new BasicCommissionRules());
		assertEquals(35.0, commission, "Expected commission to be 5% of 700.0");
	}

	@Test
	void testCalculateCommissionsForMoreThanTwoSales() {
		sellerSales.registerSeller(seller);
		sellerSales.registerSale(seller, sale);
		sellerSales.registerSale(seller,
				new Sale(seller, new Product("Phone", "P001", new Category("Electronics")), 200.0));
		sellerSales.registerSale(seller,
				new Sale(seller, new Product("Tablet", "T001", new Category("Electronics")), 300.0));

		double commission = sellerSales.calculateCommissions(seller, new BasicCommissionRules());
		assertEquals(100.0, commission, "Expected commission to be 10% of 1000.0");
	}
}
