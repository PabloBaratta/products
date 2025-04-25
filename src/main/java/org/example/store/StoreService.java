package org.example.store;

import org.example.IdProvider;
import org.example.products.Catalog;
import org.example.products.Category;
import org.example.products.Product;
import org.example.products.ProductDto;


public class StoreService {
	private final Catalog catalog;
	private final IdProvider idProvider;
	public StoreService(Catalog catalog, IdProvider idProvider) {
		this.catalog = catalog;
		this.idProvider = idProvider;
	}

	public void addProduct(ProductDto dto) {
		Product product = new Product(dto.name(), idProvider.provide(), new Category(dto.category()));
		catalog.addProduct(product, dto.price());
	}
}
