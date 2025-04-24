package org.example.products;

import java.util.Objects;

public class CategorySearchCriteria implements ProductSearchCriteria {

	private final Category category;

	public CategorySearchCriteria(Category category) {
		this.category = Objects.requireNonNull(category);
	}

	@Override
	public boolean matches(Product product) {
		return product.getCategory().equals(category);
	}
}
