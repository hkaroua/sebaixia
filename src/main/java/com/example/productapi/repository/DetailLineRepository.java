package com.example.productapi.repository;

import com.example.productapi.model.DetailLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailLineRepository extends JpaRepository<DetailLine, Long> {
}