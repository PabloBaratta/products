package org.example.cli;

import org.example.store.StoreService;

public class RegisterSaleCommand implements MenuCommand {
	private final ConsoleUi ui;
	private final StoreService storeService;

	public RegisterSaleCommand(ConsoleUi ui, StoreService storeService) {
		this.ui = ui;
		this.storeService = storeService;
	}

	@Override
	public void execute() {
		String productCode = ui.read("Product code:");
		String sellerCode = ui.read("Seller code;");

		storeService.registerSale(productCode, sellerCode);
	}
}
