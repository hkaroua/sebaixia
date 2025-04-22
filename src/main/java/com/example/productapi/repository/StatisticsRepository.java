package com.example.productapi.repository;

import com.example.productapi.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    List<Statistics> findByMetric(String metric);
    List<Statistics> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<Statistics> findByMetricAndTimestampBetween(String metric, LocalDateTime start, LocalDateTime end);
}