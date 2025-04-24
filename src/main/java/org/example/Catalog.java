package org.example;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Catalog {

	private final Map<Product, Double> prices;

	public Catalog(Map<Product, Double> prices) {
		this.prices = Objects.requireNonNull(prices, "Prices map cannot be null");
	}

	public Optional<Double> getPriceOf(Product product) {
		Objects.requireNonNull(product, "Product cannot be null");
		return Optional.ofNullable(prices.get(product));
	}
}
