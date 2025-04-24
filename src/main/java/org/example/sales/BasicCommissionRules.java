package org.example.sales;

import java.util.List;

public class BasicCommissionRules implements CommissionRules {
	@Override
	public double calculateCommission(List<Sale> sales) {
		double total = sales.stream().mapToDouble(Sale::price).sum();

		return sales.size() <= 2 ? total * 0.05 : total * 0.10;
	}
}
