package org.example.cli;

import java.util.Arrays;

public enum Option {
	ADD_PRODUCT(1), ADD_SELLER(2), REGISTER_SALE(3), CALCULATE_COMMISSION(4), SEARCH(5), SHOW_CATALOG(6), LIST_SELLERS(
			7), EXIT(0),

	UNRECOGNIZED_OPTION(-1);

	public int getIndex() {
		return index;
	}

	private final int index;

	Option(int i) {
		this.index = i;
	}

	public static Option fromInt(int value) {
		return Arrays.stream(values()).filter(v -> v.index == value).findFirst().orElse(UNRECOGNIZED_OPTION);
	}
}
