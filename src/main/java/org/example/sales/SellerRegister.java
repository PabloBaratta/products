package org.example.sales;

import org.example.IdProvider;
import org.example.products.Catalog;
import org.example.products.Product;

import java.util.Optional;

public class SellerRegister {

	private final CommissionRules rules;
	private final IdProvider idProvider;
	private final SellerSalaries sellerSalaries = new SellerSalaries();
	private final SellerSales sellerSales = new SellerSales();
	private final Catalog catalog;

	public SellerRegister(IdProvider idProvider, CommissionRules rules, Catalog catalog) {
		this.rules = rules;
		this.idProvider = idProvider;
		this.catalog = catalog;
	}

	public void register(SellerDto sellerDto, double salary) {
		Seller seller = new Seller(idProvider.provide(), sellerDto.name());
		sellerSalaries.register(seller, salary);
		sellerSales.registerSeller(seller);
	}

	public void registerSale(Seller seller, Product product) {
		double price = catalog.getPriceOf(product)
				.orElseThrow(() -> new IllegalArgumentException("Product not found in catalog"));

		Sale sale = new Sale(seller, product, price);
		sellerSales.registerSale(seller, sale);

		sellerSales.registerSale(seller, sale);
	}

	public double calculateCommissions(Seller seller) {
		return sellerSales.calculateCommissions(seller, rules);
	}

	Optional<Double> getSalaryByCode(Seller seller) {
		return sellerSalaries.getSalaryByCode(seller);
	}
}
