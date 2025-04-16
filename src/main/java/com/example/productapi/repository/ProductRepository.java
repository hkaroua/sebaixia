package com.example.productapi.repository;

import com.example.productapi.model.DecompositionComercial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<DecompositionComercial, Long> {
}
