package org.example.store;

import org.example.IdProvider;
import org.example.products.Catalog;
import org.example.products.Category;
import org.example.products.Product;
import org.example.products.ProductDto;
import org.example.sales.Seller;
import org.example.sales.SellerRegister;

public class StoreService {
	private final Catalog catalog;
	private final IdProvider idProvider;
	private final SellerRegister sellerRegister;

	public StoreService(Catalog catalog, IdProvider idProvider, SellerRegister sellerRegister) {
		this.catalog = catalog;
		this.idProvider = idProvider;
		this.sellerRegister = sellerRegister;
	}

	public void addProduct(ProductDto dto) {
		Product product = new Product(dto.name(), idProvider.provide(), new Category(dto.category()));
		catalog.addProduct(product, dto.price());
	}

	public void registerSale(String productCode, String sellerCode) {
		Product product = catalog.findBy(p -> p.getCode().equals(productCode)).stream().findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Product not found"));

		Seller seller = sellerRegister.findByCode(sellerCode)
				.orElseThrow(() -> new IllegalArgumentException("Seller not found"));

		sellerRegister.registerSale(seller, product);
	}
}
