package org.example.cli;

import org.example.products.ProductDto;
import org.example.store.StoreService;

public class AddProductCommand implements MenuCommand {

	private final ConsoleUi ui;
	private final StoreService store;

	public AddProductCommand(ConsoleUi ui, StoreService store) {
		this.ui = ui;
		this.store = store;
	}
	@Override
	public void execute() {
		String name = ui.read("Name:");
		String category = ui.read("Category:");
		double price = ui.readDouble("Price: ");
		store.addProduct(new ProductDto(name, price, category));
	}
}
