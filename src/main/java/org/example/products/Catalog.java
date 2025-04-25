package org.example.products;

import java.util.List;
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

	public List<Product> findBy(ProductSearchCriteria criteria) {
		Objects.requireNonNull(criteria, "Search criteria cannot be null");

		return prices.keySet().stream().filter(criteria::matches).toList();
	}

	 public void addProduct(Product product, double price) {
        Objects.requireNonNull(product, "Product cannot be null");
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than zero");
        prices.put(product, price);
    }
}
