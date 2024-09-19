package com.example.sbtest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {


    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable long id) {
        log.info("Fetching product by id {}",id);
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> getByTitle(@RequestParam(value = "title") String title) {
        log.info("Searching product by title {}",title);
        return ResponseEntity.ok(productService.getByTitle(title));
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Valid Product product) {
        log.info("Adding product {}",product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.add(product));
    }

    @PutMapping
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid Product product) {
        log.info("Updating product {}",product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }
}
