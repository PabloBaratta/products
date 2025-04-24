package org.example.sales;

import org.example.products.Product;

public record Sale(Seller seller, Product product, double price) {
}
