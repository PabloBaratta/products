package org.example.cli;

import org.example.sales.SellerDto;
import org.example.sales.SellerRegister;

public class AddSellerCommand implements MenuCommand {
	private final ConsoleUi ui;
	private final SellerRegister sellerRegister;

	public AddSellerCommand(ConsoleUi ui, SellerRegister sellerRegister) {
		this.ui = ui;
		this.sellerRegister = sellerRegister;
	}
	@Override
	public void execute() {
		String name = ui.read("Name:");
		double price = ui.readDouble("Salary: ");
		sellerRegister.register(new SellerDto(name, price));
	}
}
