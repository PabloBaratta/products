package org.example.cli;

import org.example.sales.SellerRegister;

public class CalculateCommissionCommand implements MenuCommand {

	private final SellerRegister register;
	private final ConsoleUi ui;

	public CalculateCommissionCommand(ConsoleUi ui, SellerRegister register) {
		this.ui = ui;
		this.register = register;
	}
	@Override
	public void execute() {
		String code = ui.read("Introduce seller code");
		double commissions = register.calculateCommissions(code);
		ui.print(String.format("The total commissions for seller with code '%s' are: $%.2f", code, commissions));
	}
}
