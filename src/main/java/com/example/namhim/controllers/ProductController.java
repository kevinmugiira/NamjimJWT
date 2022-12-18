package com.example.namhim.controllers;

import com.example.namhim.models.Product;
import com.example.namhim.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public ProductRepo productRepo;

    @GetMapping
    public List<Product> list() {
        return productRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid Product product) {
        Product savedProduct = productRepo.save(product);
        URI productURI = URI.create("/products/" + savedProduct.getId());

        return ResponseEntity.created(productURI).body(savedProduct);
    }
}
