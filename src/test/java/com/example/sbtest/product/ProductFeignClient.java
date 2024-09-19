package com.example.sbtest.product;

import com.example.sbtest.Product;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Scope(SCOPE_CUCUMBER_GLUE)
@FeignClient(name = "PRODUCT-SERVICE", url = "${feign.client.url}")
public interface ProductFeignClient {

    String API_BASE_URI = "/api/products";

    @GetMapping(API_BASE_URI)
    ResponseEntity<List<Product>> getAll();

    @GetMapping(API_BASE_URI + "/{id}")
    ResponseEntity<Product> getById(@PathVariable long id);

    @GetMapping(API_BASE_URI + "/search")
    ResponseEntity<List<Product>> getByTitle(@RequestParam(value = "title") String title);

    @PostMapping(API_BASE_URI)
    ResponseEntity<Product> addProduct(@RequestBody @Valid Product product);

    @PutMapping(API_BASE_URI)
    ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product);


}