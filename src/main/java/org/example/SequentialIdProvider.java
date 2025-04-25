package org.example;

public class SequentialIdProvider implements IdProvider {

	int count = 0;
	@Override
	public String provide() {
		return String.valueOf(count++);
	}
}
