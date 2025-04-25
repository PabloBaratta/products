package org.example.cli;

import org.example.sales.SalesService;
import org.example.sales.SellerRegister;

public class CalculateCommissionCommand implements MenuCommand {

	private final ConsoleUi ui;
	private final SalesService service;

	public CalculateCommissionCommand(ConsoleUi ui, SalesService service) {
		this.ui = ui;
		this.service = service;
	}
	@Override
	public void execute() {
		String code = ui.read("Introduce seller code");
		double commissions = service.calculateCommissions(code);
		ui.print(String.format("The total commissions for seller with code '%s' are: $%.2f", code, commissions));
	}
}
