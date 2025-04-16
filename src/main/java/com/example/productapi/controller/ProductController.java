package com.example.productapi.controller;

import com.example.productapi.service.ProductService;
import com.example.productapi.model.DecompositionComercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public DecompositionComercial createProduct(@RequestBody DecompositionComercial product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Optional<DecompositionComercial> getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
}