package org.example.products;

import java.util.Objects;

public class Product {

	private final Category category;
	private final String code;
	private final String name;

	public Product(String name, String code, Category category) {
		this.name = name;
		this.code = code;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Product product = (Product) o;
		return Objects.equals(code, product.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}
}
