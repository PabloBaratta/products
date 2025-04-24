package org.example.products;

//Given that in the future we would like to do
//something else with category like adding descriptions or hierarchy
public record Category(String name) {

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Category category = (Category) o;
		return name.equalsIgnoreCase(category.name);
	}

}
