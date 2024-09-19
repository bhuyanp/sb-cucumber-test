package com.example.sbtest;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {
    private static final List<Product> products = Collections.synchronizedList(List.of(
            new Product(1, "PRODUCT 1", 11.25),
            new Product(2, "PRODUCT 2", 12.25),
            new Product(3, "PRODUCT 3", 13.25),
            new Product(4, "PRODUCT 4", 14.25)
    ));

    public List<Product> getAll() {
        return products;
    }

    public Product getById(long id) {
        return products.stream()
                .filter(it -> it.getId() == id).findAny()
                .orElseThrow(()->new NoSuchElementException("No product found with id "+id));
    }

    public List<Product> getByTitle(String title) {
        return products.stream()
                .filter(it -> it.getTitle().equalsIgnoreCase(title)).toList();
    }

    public Product add(Product product) {
        Product productWithMaxId = products.stream()
                .max((a, b) -> a.getId() > b.getId() ? 1 : 0).orElseThrow();
        product.setId(productWithMaxId.getId() + 1);
        products.add(product);
        return product;
    }

    public Product save(Product product) {
        Product oldProduct = getById(product.getId());
        products.add(product);
        return product;
    }
}
