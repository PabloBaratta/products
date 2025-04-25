package org.example.cli;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Menu {
	private final Map<Option, MenuCommand> commands = new EnumMap<>(Option.class);
	private final ConsoleUi ui;

	public Menu(ConsoleUi ui) {
		this.ui = ui;
	}

	public void register(Option option, MenuCommand command) {
		commands.put(option, command);
	}

	public void execute(Option option) {
		MenuCommand menuCommand = commands.get(option);
		if (menuCommand != null) {
			try {
				menuCommand.execute();
			} catch (Exception e) {
				ui.printError("Error: " + e.getMessage());
			}
		} else if (option == Option.EXIT) {
			System.exit(0);
		} else {
			ui.printError("Invalid Option");
		}
	}

	@Override
	public String toString() {
		return commands.keySet().stream().filter(k -> k.getIndex() > 0)
				.map(k -> k.getIndex() + ". " + k.name().replace("_", " ")).collect(Collectors.joining("\n"));
	}

}
