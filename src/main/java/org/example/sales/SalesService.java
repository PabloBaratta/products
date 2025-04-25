package org.example.sales;

import org.example.products.Catalog;
import org.example.products.Product;

public class SalesService {

    private final Catalog catalog;
    private final SellerRegister sellerRegister;
    private final CommissionRules commissionRules;

    public SalesService(Catalog catalog, SellerRegister sellerRegister, CommissionRules commissionRules) {
        this.catalog = catalog;
        this.sellerRegister = sellerRegister;
        this.commissionRules = commissionRules;
    }

    public void registerSale(String productCode, String sellerCode) {
        Product product = catalog.findBy(p -> p.getCode().equals(productCode))
                .stream().findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Seller seller = sellerRegister.findByCode(sellerCode)
                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));

        double price = catalog.getPriceOf(product)
                .orElseThrow(() -> new IllegalArgumentException("Price not found"));

        sellerRegister.registerSale(seller, new Sale(seller, product, price));
    }

    public double calculateCommissions(String sellerCode) {
        Seller seller = sellerRegister.findByCode(sellerCode)
                .orElseThrow(() -> new IllegalArgumentException("Seller not found"));
        return commissionRules.calculateCommission(sellerRegister.getSalesBy(seller));
    }
}

