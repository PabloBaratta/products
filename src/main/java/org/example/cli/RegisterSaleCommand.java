package org.example.cli;

import org.example.sales.SalesService;

public class RegisterSaleCommand implements MenuCommand {
	private final ConsoleUi ui;
	private final SalesService salesService;

	public RegisterSaleCommand(ConsoleUi ui, SalesService salesService) {
		this.ui = ui;
		this.salesService = salesService;
	}

	@Override
	public void execute() {
		String productCode = ui.read("Product code:");
		String sellerCode = ui.read("Seller code;");

		salesService.registerSale(productCode, sellerCode);
	}
}
