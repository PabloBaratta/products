package org.example.sales;

import java.util.Objects;

public class Seller {

	private final String code;
	private final String name;

	public Seller(String code, String name) {
		this.code = code;
		this.name = name;
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
		Seller seller = (Seller) o;
		return Objects.equals(code, seller.code);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}
}