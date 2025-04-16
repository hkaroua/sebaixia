package com.example.productapi.service;

import com.example.productapi.model.DecompositionComercial;
import com.example.productapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public DecompositionComercial createProduct(DecompositionComercial product) {
        return productRepository.save(product);
    }

    public Optional<DecompositionComercial> getProduct(Long id) {
        return productRepository.findById(id);
    }
}
