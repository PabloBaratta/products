package org.example;

import java.util.Objects;

//Given that in the future we would like to do
//something else with category like adding descriptions or hierarchy
public class Category {

	private final String name;

	public Category(String name) {
		this.name = name;
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
		Category category = (Category) o;
		return name.equalsIgnoreCase(category.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

}
