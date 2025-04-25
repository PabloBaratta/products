package org.example.cli;

import org.example.products.Catalog;

import java.util.List;

public class ShowCatalogCommand implements MenuCommand {

	private final ConsoleUi ui;
	private final Catalog catalog;

	public ShowCatalogCommand(ConsoleUi ui, Catalog catalog) {
		this.ui = ui;
		this.catalog = catalog;
	}

	@Override
	public void execute() {
		ui.print("Catalog of products:");
		List<Catalog.ProductWithPrice> productsWithPrice = catalog.getAllProducts();

		if (productsWithPrice.isEmpty()) {
			ui.print("No products in catalog.");
		} else {
			for (Catalog.ProductWithPrice productWithPrice : productsWithPrice) {
				ui.print(productWithPrice.toString());
			}
		}
	}
}
