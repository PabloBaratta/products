package org.example.sales;

import org.example.SequentialIdProvider;
import org.example.products.Category;
import org.example.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SellerRegisterTest {

        private SellerRegister sellerRegister;

        @BeforeEach
        void setUp() {
            sellerRegister = new SellerRegister(new SequentialIdProvider());
        }

        @Test
        void registersSellerCorrectly() {
            SellerDto dto = new SellerDto("Alice", 1500.0);
            sellerRegister.register(dto);

            Seller seller = sellerRegister.findByCode("0").orElseThrow();
            assertEquals("Alice", seller.getName());
            assertEquals(1500.0, sellerRegister.getSalaryBy(seller).orElse(0.0));
        }

        @Test
        void registerSaleAddsSaleToSeller() {
            SellerDto dto = new SellerDto("Bob", 2000.0);
            sellerRegister.register(dto);

            Seller seller = sellerRegister.findByCode("0").orElseThrow();
            Product product = new Product("Laptop", "P-123", new Category("Electronics"));
            Sale sale = new Sale(seller, product, 1000.0);

            sellerRegister.registerSale(seller, sale);

            List<Sale> sales = sellerRegister.getSalesBy(seller);
            assertEquals(1, sales.size());
            assertEquals(1000.0, sales.get(0).price());
            assertEquals("Laptop", sales.get(0).product().getName());
        }

        @Test
        void getAllSellersIncludesRegisteredSellers() {
            sellerRegister.register(new SellerDto("Carlos", 1800.0));

            Set<SellerRegister.SellerInformation> sellers = sellerRegister.getAllSellers();
            assertEquals(1, sellers.size());

            SellerRegister.SellerInformation info = sellers.iterator().next();
            assertEquals("Carlos", info.name());
            assertEquals(1800.0, info.salary());
            assertTrue(info.sales().isEmpty());
        }

}