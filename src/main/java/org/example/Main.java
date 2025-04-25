package org.example;

import org.example.cli.*;
import org.example.products.Catalog;
import org.example.sales.BasicCommissionRules;
import org.example.sales.SalesService;
import org.example.sales.SellerRegister;
import org.example.store.StoreService;

public class Main {
	public static void main(String[] args) {
		var ui = new ConsoleUi();
		var c = new Catalog();
		var sellerIdProvider = new SequentialIdProvider();
		var productIdProvider = new SequentialIdProvider();
		var sellerRegister = new SellerRegister(sellerIdProvider);
		var salesService = new SalesService(c, sellerRegister, new BasicCommissionRules());
		var store = new StoreService(c, productIdProvider);
		var menu = getMenu(ui, store, sellerRegister, c, salesService);

		while (true) {
			ui.print("Select an option");
			ui.print(menu.toString());
			var option = ui.readOption();
			menu.execute(option);
		}
	}

	private static Menu getMenu(ConsoleUi ui, StoreService store, SellerRegister sellerRegister, Catalog catalog, SalesService service) {
		var menu = new Menu(ui);
		menu.register(Option.ADD_PRODUCT, new AddProductCommand(ui, store));
		menu.register(Option.ADD_SELLER, new AddSellerCommand(ui, sellerRegister));
		menu.register(Option.REGISTER_SALE, new RegisterSaleCommand(ui, service));
		menu.register(Option.SHOW_CATALOG, new ShowCatalogCommand(ui, catalog));
		menu.register(Option.SEARCH, new SearchCommand(ui, catalog));
		menu.register(Option.LIST_SELLERS, new ListSellersCommand(ui, sellerRegister));
		menu.register(Option.CALCULATE_COMMISSION, new CalculateCommissionCommand(ui, service));
		return menu;
	}
}