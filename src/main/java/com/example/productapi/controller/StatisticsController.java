package com.example.productapi.controller;

import com.example.productapi.model.stats.Statistics;
import com.example.productapi.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Tag(name = "Statistics API", description = "Endpoints for retrieving product statistics")
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @Operation(summary = "Get statistics", description = "Get statistics for all products within a date range")
    @GetMapping
    public ResponseEntity<Statistics> getStatistics(
            @Parameter(description = "Start date (yyyy-MM-dd'T'HH:mm:ss)")
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            
            @Parameter(description = "End date (yyyy-MM-dd'T'HH:mm:ss)")
            @RequestParam(required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(statisticsService.generateStatistics(startDate, endDate));
    }

    @Operation(summary = "Get average price for product", description = "Get the average price for a specific product")
    @GetMapping("/product/{productName}/average-price")
    public ResponseEntity<Double> getAveragePriceForProduct(
            @Parameter(description = "Name of the product")
            @PathVariable String productName) {
        Double averagePrice = statisticsService.getAveragePriceForProduct(productName);
        return averagePrice != null ? ResponseEntity.ok(averagePrice) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get average prices by category", description = "Get average prices for all products in a category")
    @GetMapping("/category/{category}/average-prices")
    public ResponseEntity<Map<String, Double>> getAveragePricesForCategory(
            @Parameter(description = "Category name")
            @PathVariable String category) {
        Map<String, Double> averagePrices = statisticsService.getAveragePricesForCategory(category);
        return averagePrices != null ? ResponseEntity.ok(averagePrices) : ResponseEntity.notFound().build();
    }
}
