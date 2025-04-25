package org.example.cli;

import org.example.products.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class SearchCommand implements MenuCommand {

    private final ConsoleUi ui;
    private final Catalog catalog;
    private final Map<String, Supplier<ProductSearchCriteria>> criteriaMap;

    public SearchCommand(ConsoleUi ui, Catalog catalog) {
        this.ui = ui;
        this.catalog = catalog;
        this.criteriaMap = new LinkedHashMap<>();
        criteriaMap.put("Name contains", this::createNameCriteria);
        criteriaMap.put("Category name", this::createCategoryCriteria);
    }

    @Override
    public void execute() {
        List<String> options = printOptions();

        String input = ui.read("Option:");
        int option;
        try {
            option = Integer.parseInt(input);
            if (option < 1 || option > options.size()) {
                ui.print("Invalid option.");
                return;
            }
        } catch (NumberFormatException e) {
            ui.print("Invalid number format.");
            return;
        }

        Supplier<ProductSearchCriteria> supplier = criteriaMap.get(options.get(option - 1));
        ProductSearchCriteria criteria = supplier.get();

        List<Product> results = catalog.findBy(criteria);

        if (results.isEmpty()) {
            ui.print("No products found.");
        } else {
            ui.print("Products found:");
            results.forEach(product -> catalog.getPriceOf(product).ifPresent(price ->
                    ui.print(product + " - $" + price)));
        }
    }

    private List<String> printOptions() {
        ui.print("Search by:");

        int i = 1;
        List<String> keys = new ArrayList<>(criteriaMap.keySet());
        for (String key : keys) {
            ui.print(i + ". " + key);
            i++;
        }
        return keys;
    }

    private ProductSearchCriteria createNameCriteria() {
        String substring = ui.read("Enter part of the product name:");
        return new NameContainsSearchCriteria(substring);
    }

    private ProductSearchCriteria createCategoryCriteria() {
        String category = ui.read("Enter category name:");
        return new CategorySearchCriteria(new Category(category));
    }
}
