package org.example.products;

public class NameContainsSearchCriteria implements ProductSearchCriteria {

	private final String substring;

	public NameContainsSearchCriteria(String substring) {
		this.substring = substring.toLowerCase();
	}

	@Override
	public boolean matches(Product product) {
		return product.getName().toLowerCase().contains(substring);
	}
}
