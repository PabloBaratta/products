package org.example.cli;

import org.example.sales.SellerRegister;

public class ListSellersCommand implements MenuCommand {

	private final ConsoleUi ui;
	private final SellerRegister sellerRegister;

	public ListSellersCommand(ConsoleUi ui, SellerRegister sellerRegister) {
		this.ui = ui;
		this.sellerRegister = sellerRegister;
	}
	@Override
	public void execute() {
		ui.print("Sellers Information");
		ui.print(sellerRegister.getAllSellers().toString());
	}
}
