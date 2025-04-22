package com.example.productapi.repository;

import com.example.productapi.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findByDesignation(String designation);
    List<Detail> findByProductId(Long productId);
}
