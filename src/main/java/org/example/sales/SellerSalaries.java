package org.example.sales;

import java.util.*;

class SellerSalaries {

	private final Map<Seller, Double> salaries = new HashMap<>();

	void register(Seller seller, double salary) {
		Objects.requireNonNull(seller, "Seller cannot be null");
		salaries.put(seller, salary);
	}

	Optional<Double> getSalaryByCode(Seller seller) {
		Objects.requireNonNull(seller, "Seller cannot be null");
		return Optional.ofNullable(salaries.get(seller));
	}

}