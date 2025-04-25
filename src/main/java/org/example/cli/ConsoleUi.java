package org.example.cli;

import java.util.Scanner;

public class ConsoleUi {
	private final Scanner scanner = new Scanner(System.in);

	public String read(String message) {
		System.out.println(message);
		return scanner.nextLine();
	}

	public double readDouble(String message) {
		System.out.print(message);
		return Double.parseDouble(scanner.nextLine());
	}

	public Option readOption() {
		System.out.println("Option: ");
		try {
			int op = Integer.parseInt(scanner.nextLine());
			return Option.fromInt(op);
		} catch (NumberFormatException e) {
			return Option.UNRECOGNIZED_OPTION;
		}
	}
	public void printError(String message) {
		System.out.println(message);
	}

	public void print(String message) {
		System.out.println(message);
	}
}
