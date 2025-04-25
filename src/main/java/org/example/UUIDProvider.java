package org.example;

import java.util.UUID;

public class UUIDProvider implements IdProvider {
	@Override
	public String provide() {
		return UUID.randomUUID().toString();
	}
}
