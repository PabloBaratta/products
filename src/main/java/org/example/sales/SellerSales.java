package org.example.sales;

import java.util.*;

class SellerSales {

	private final Map<Seller, List<Sale>> sales = new HashMap<>();

	void registerSeller(Seller seller) {
		Objects.requireNonNull(seller, "Seller cannot be null");
		if (isSellerRegistered(seller))
			throw new IllegalArgumentException("Seller already exists");
		sales.put(seller, new LinkedList<>());
	}

	void registerSale(Seller seller, Sale sale) {
		Objects.requireNonNull(seller, "Seller cannot be null");
		Objects.requireNonNull(sale, "Sale cannot be null");
		if (!isSellerRegistered(seller))
			throw new IllegalArgumentException("Seller does not exists in the registry");
		sales.computeIfAbsent(seller, k -> new LinkedList<>()).add(sale);
	}

	double calculateCommissions(Seller seller, CommissionRules rule) {
		Objects.requireNonNull(seller, "Seller cannot be null");

		List<Sale> sellerSales = sales.getOrDefault(seller, List.of());
		return rule.calculateCommission(sellerSales);
	}

	private boolean isSellerRegistered(Seller seller) {
		return sales.containsKey(seller);
	}

}
