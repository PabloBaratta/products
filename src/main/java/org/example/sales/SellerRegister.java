package org.example.sales;

import org.example.IdProvider;
import org.example.products.Catalog;
import org.example.products.Product;

import java.util.*;

public class SellerRegister {

	private final CommissionRules rules;
	private final IdProvider idProvider;
	private final SellerSalaries sellerSalaries = new SellerSalaries();
	private final SellerSales sellerSales = new SellerSales();
	private final Map<String, Seller> sellers = new HashMap<>();
	private final Catalog catalog;

	public SellerRegister(IdProvider idProvider, CommissionRules rules, Catalog catalog) {
		this.rules = rules;
		this.idProvider = idProvider;
		this.catalog = catalog;
	}

	public void register(SellerDto sellerDto) {
		Seller seller = new Seller(idProvider.provide(), sellerDto.name());
		sellers.put(seller.getCode(), seller);
		sellerSalaries.register(seller, sellerDto.salary());
		sellerSales.registerSeller(seller);
	}

	public void registerSale(Seller seller, Product product) {
		double price = catalog.getPriceOf(product)
				.orElseThrow(() -> new IllegalArgumentException("Product not found in catalog"));

		Sale sale = new Sale(seller, product, price);
		sellerSales.registerSale(seller, sale);
	}


	public double calculateCommissions(String sellerCode) {
		Seller seller = Optional.ofNullable(sellers.get(sellerCode))
				.orElseThrow(() -> new IllegalArgumentException("Seller not found"));
		return sellerSales.calculateCommissions(seller, rules);
	}

	Optional<Double> getSalaryByCode(Seller seller) {
		return sellerSalaries.getSalaryByCode(seller);
	}

	public Optional<Seller> findByCode(String sellerCode) {
		return Optional.ofNullable(sellers.get(sellerCode));
	}

	public Set<SellerInformation> getAllSellers() {
		Set<SellerInformation> sellersWithDetails = new HashSet<>();
		for (Seller seller : sellers.values()) {
			Optional<Double> salary = sellerSalaries.getSalaryByCode(seller);
			Optional<List<Sale>> sales = sellerSales.getSales(seller);
			SellerInformation sellerWithDetails = new SellerInformation(seller.getCode(), seller.getName(),
					salary.orElse(0.0), sales.orElse(List.of()));
			sellersWithDetails.add(sellerWithDetails);
		}
		return sellersWithDetails;
	}
	public record SellerInformation(String code, String name, double salary, List<Sale> sales) {
	}
}
