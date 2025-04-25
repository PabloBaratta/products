package org.example.cli;

import org.example.products.Catalog;

public class AddProductCommand implements MenuCommand {

	private final ConsoleUi ui;

	public AddProductCommand(ConsoleUi ui, Catalog c) {
		this.ui = ui;
	}
	@Override
	public void execute() {

	}
}
