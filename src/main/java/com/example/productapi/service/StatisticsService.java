package com.example.productapi.service;

import com.example.productapi.model.Detail;
import com.example.productapi.model.stats.Statistics;
import com.example.productapi.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class StatisticsService {
    
    @Autowired
    private DetailRepository detailRepository;

    @Cacheable(value = "statistics", key = "#startDate + '_' + #endDate")
    public Statistics generateStatistics(LocalDateTime startDate, LocalDateTime endDate) {
        Statistics stats = new Statistics();
        stats.setStartDate(startDate);
        stats.setEndDate(endDate);

        List<Detail> details = detailRepository.findAll();
        List<Detail> filteredDetails = stats.filterByDateRange(details);

        // Parallel computation of different statistics
        CompletableFuture<Map<String, Double>> avgPricesFuture = CompletableFuture
            .supplyAsync(() -> stats.calculateAvgPricesPerProduct(filteredDetails));

        CompletableFuture<Map<String, Statistics.StatisticalSummary>> statsFuture = CompletableFuture
            .supplyAsync(() -> stats.calculateProductStatistics(filteredDetails));

        CompletableFuture<Map<String, Statistics.TrendAnalysis>> trendsFuture = CompletableFuture
            .supplyAsync(() -> stats.analyzeTrends(filteredDetails));

        CompletableFuture<Map<String, Double>> marketShareFuture = CompletableFuture
            .supplyAsync(() -> stats.calculateMarketShare(filteredDetails));

        CompletableFuture<Map<String, Double>> revenueFuture = CompletableFuture
            .supplyAsync(() -> stats.calculateRevenueByCategory(filteredDetails));

        // Wait for all computations to complete and set results
        CompletableFuture.allOf(
            avgPricesFuture, 
            statsFuture, 
            trendsFuture, 
            marketShareFuture, 
            revenueFuture
        ).join();

        stats.setAvgPricesPerProduct(avgPricesFuture.join());
        stats.setProductStatistics(statsFuture.join());
        stats.setTrends(trendsFuture.join());
        stats.setMarketShare(marketShareFuture.join());
        stats.setRevenueByCategory(revenueFuture.join());

        return stats;
    }

    @Cacheable(value = "productStatistics", key = "#productName")
    public Double getAveragePriceForProduct(String productName) {
        List<Detail> details = detailRepository.findAll();
        Statistics stats = new Statistics();
        Map<String, Double> avgPrices = stats.calculateAvgPricesPerProduct(details);
        return avgPrices.get(productName);
    }

    @Cacheable(value = "categoryStatistics", key = "#category")
    public Map<String, Double> getAveragePricesForCategory(String category) {
        List<Detail> details = detailRepository.findAll();
        Statistics stats = new Statistics();
        Map<String, Map<String, Double>> pricesByCategory = stats.calculateAvgPricesPerProductByCategory(details);
        return pricesByCategory.get(category);
    }
}