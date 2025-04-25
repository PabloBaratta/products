package org.example;

import org.example.cli.AddProductCommand;
import org.example.cli.ConsoleUi;
import org.example.cli.Menu;
import org.example.cli.Option;
import org.example.products.Catalog;

public class Main {
	public static void main(String[] args) {
		var ui = new ConsoleUi();
		var menu = new Menu();
		Catalog c = new Catalog();

		menu.register(Option.ADD_PRODUCT, new AddProductCommand(ui, c));

		while (true) {
			ui.print("Select an option");
			ui.print(menu.toString());
			var option = ui.readOption();
			menu.execute(option);
		}
	}
}