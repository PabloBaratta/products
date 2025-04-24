package org.example.sales;

import org.example.products.Category;
import org.example.products.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommissionRulesTest {

	private final CommissionRules rule = new BasicCommissionRules();
	Seller seller = new Seller("001", "John Doe");

	@Test
	void noSalesShouldReturnZeroCommission() {
		List<Sale> noSales = List.of();
		double commission = rule.calculateCommission(noSales);
		assertEquals(0.0, commission, "Expected 0 commission for no sales");
	}

	@Test
	void oneSaleShouldReturnFivePercentCommission() {
		List<Sale> sales = List.of(new Sale(seller, new Product("A", "001", new Category("X")), 100.0));
		double commission = rule.calculateCommission(sales);
		assertEquals(5.0, commission, 0.0001);
	}

	@Test
	void twoSalesShouldReturnFivePercentCommission() {
		List<Sale> sales = List.of(new Sale(seller, new Product("A", "001", new Category("X")), 100.0),
				new Sale(seller, new Product("B", "002", new Category("X")), 200.0));
		double commission = rule.calculateCommission(sales);
		assertEquals(15.0, commission, 0.0001); // 5% of 300
	}
}
