package org.example.sales;

import org.example.IdProvider;

import java.util.*;

public class SellerRegister {

	private final IdProvider idProvider;
	private final SellerSalaries sellerSalaries = new SellerSalaries();
	private final SellerSales sellerSales = new SellerSales();
	private final Map<String, Seller> sellers = new HashMap<>();

	public SellerRegister(IdProvider idProvider) {
		this.idProvider = idProvider;
	}

	public void register(SellerDto sellerDto) {
		Seller seller = new Seller(idProvider.provide(), sellerDto.name());
		sellers.put(seller.getCode(), seller);
		sellerSalaries.register(seller, sellerDto.salary());
		sellerSales.registerSeller(seller);
	}

	public Optional<Seller> findByCode(String sellerCode) {
		return Optional.ofNullable(sellers.get(sellerCode));
	}

	public Optional<Double> getSalaryBy(Seller seller) {
		return sellerSalaries.getSalaryByCode(seller);
	}

	public List<Sale> getSalesBy(Seller seller) {
		return sellerSales.getSales(seller).orElse(List.of());
	}

	void registerSale(Seller seller, Sale sale) {
		sellerSales.registerSale(seller, sale);
	}

	public Set<SellerInformation> getAllSellers() {
		Set<SellerInformation> result = new HashSet<>();
		for (Seller seller : sellers.values()) {
			double salary = getSalaryBy(seller).orElse(0.0);
			List<Sale> sales = getSalesBy(seller);
			result.add(new SellerInformation(seller.getCode(), seller.getName(), salary, sales));
		}
		return result;
	}

	public record SellerInformation(String code, String name, double salary, List<Sale> sales) {}
}
