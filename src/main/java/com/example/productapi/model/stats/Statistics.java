package com.example.productapi.model.stats;

import com.example.productapi.model.Detail;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class Statistics {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Map<String, Double> avgPricesPerProduct;
    private Map<String, Map<String, Double>> avgPricesPerProductByCategory;
    private Map<String, StatisticalSummary> productStatistics;
    private Map<String, TrendAnalysis> trends;
    private Map<String, Double> marketShare;
    private Map<String, Double> revenueByCategory;

    public Statistics() {
        this.avgPricesPerProduct = new HashMap<>();
        this.avgPricesPerProductByCategory = new HashMap<>();
        this.productStatistics = new HashMap<>();
        this.trends = new HashMap<>();
        this.marketShare = new HashMap<>();
        this.revenueByCategory = new HashMap<>();
    }

    @Data
    @Builder
    public static class StatisticalSummary {
        private double mean;
        private double median;
        private double standardDeviation;
        private double min;
        private double max;
        private int count;
    }

    @Data
    @Builder
    public static class TrendAnalysis {
        private double growthRate;
        private List<Point> trendPoints;
        private String trend; // "UP", "DOWN", "STABLE"
        private double volatility;

        @Data
        @AllArgsConstructor
        public static class Point {
            private LocalDateTime date;
            private double value;
        }
    }

    public Map<String, Double> calculateAvgPricesPerProduct(List<Detail> details) {
        return details.stream()
            .collect(Collectors.groupingBy(
                detail -> detail.getDecompositionComercial().getName(),
                Collectors.averagingDouble(Detail::getTotalPrice)
            ));
    }

    public Map<String, Map<String, Double>> calculateAvgPricesPerProductByCategory(List<Detail> details) {
        return details.stream()
            .collect(Collectors.groupingBy(
                detail -> detail.getDecompositionComercial().getCategory(),
                Collectors.groupingBy(
                    detail -> detail.getDecompositionComercial().getName(),
                    Collectors.averagingDouble(Detail::getTotalPrice)
                )
            ));
    }

    public Map<String, StatisticalSummary> calculateProductStatistics(List<Detail> details) {
        return details.stream()
            .collect(Collectors.groupingBy(
                detail -> detail.getDecompositionComercial().getName(),
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    this::calculateStatistics
                )
            ));
    }

    private StatisticalSummary calculateStatistics(List<Detail> details) {
        List<Double> prices = details.stream()
            .map(Detail::getTotalPrice)
            .sorted()
            .collect(Collectors.toList());

        double mean = prices.stream().mapToDouble(d -> d).average().orElse(0.0);
        double median = calculateMedian(prices);
        double stdDev = calculateStandardDeviation(prices, mean);

        return StatisticalSummary.builder()
            .mean(mean)
            .median(median)
            .standardDeviation(stdDev)
            .min(prices.isEmpty() ? 0 : prices.get(0))
            .max(prices.isEmpty() ? 0 : prices.get(prices.size() - 1))
            .count(prices.size())
            .build();
    }

    private double calculateMedian(List<Double> sortedPrices) {
        if (sortedPrices.isEmpty()) return 0.0;
        int middle = sortedPrices.size() / 2;
        if (sortedPrices.size() % 2 == 1) {
            return sortedPrices.get(middle);
        }
        return (sortedPrices.get(middle - 1) + sortedPrices.get(middle)) / 2.0;
    }

    private double calculateStandardDeviation(List<Double> prices, double mean) {
        if (prices.isEmpty()) return 0.0;
        double sumSquaredDiff = prices.stream()
            .mapToDouble(price -> Math.pow(price - mean, 2))
            .sum();
        return Math.sqrt(sumSquaredDiff / prices.size());
    }

    public Map<String, TrendAnalysis> analyzeTrends(List<Detail> details) {
        return details.stream()
            .collect(Collectors.groupingBy(
                detail -> detail.getDecompositionComercial().getName(),
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    this::analyzeTrend
                )
            ));
    }

    private TrendAnalysis analyzeTrend(List<Detail> details) {
        List<TrendAnalysis.Point> points = details.stream()
            .sorted(Comparator.comparing(Detail::getCreatedAt))
            .map(detail -> new TrendAnalysis.Point(
                detail.getCreatedAt(),
                detail.getTotalPrice()
            ))
            .collect(Collectors.toList());

        double growthRate = calculateGrowthRate(points);
        String trend = determineTrend(growthRate);

        return TrendAnalysis.builder()
            .growthRate(growthRate)
            .trendPoints(points)
            .trend(trend)
            .build();
    }

    private double calculateGrowthRate(List<TrendAnalysis.Point> points) {
        if (points.size() < 2) return 0.0;
        double firstPrice = points.get(0).getValue();
        double lastPrice = points.get(points.size() - 1).getValue();
        return ((lastPrice - firstPrice) / firstPrice) * 100;
    }

    private String determineTrend(double growthRate) {
        if (Math.abs(growthRate) < 1.0) return "STABLE";
        return growthRate > 0 ? "UP" : "DOWN";
    }

    public Map<String, Double> calculateMarketShare(List<Detail> details) {
        double totalRevenue = details.stream()
            .mapToDouble(Detail::getTotalPrice)
            .sum();

        return details.stream()
            .collect(Collectors.groupingBy(
                detail -> detail.getDecompositionComercial().getName(),
                Collectors.collectingAndThen(
                    Collectors.summingDouble(Detail::getTotalPrice),
                    revenue -> (revenue / totalRevenue) * 100
                )
            ));
    }

    public Map<String, Double> calculateRevenueByCategory(List<Detail> details) {
        return details.stream()
            .collect(Collectors.groupingBy(
                detail -> detail.getDecompositionComercial().getCategory(),
                Collectors.summingDouble(Detail::getTotalPrice)
            ));
    }

    public List<Detail> filterByDateRange(List<Detail> details) {
        if (startDate == null || endDate == null) {
            return details;
        }
        return details.stream()
            .filter(detail -> {
                LocalDateTime detailDate = detail.getCreatedAt();
                return detailDate != null &&
                       !detailDate.isBefore(startDate) &&
                       !detailDate.isAfter(endDate);
            })
            .collect(Collectors.toList());
    }

    public Double getAveragePriceForProduct(String productName) {
        return avgPricesPerProduct != null ? avgPricesPerProduct.get(productName) : null;
    }

    public Map<String, Double> getAveragePricesForCategory(String category) {
        return avgPricesPerProductByCategory != null ? avgPricesPerProductByCategory.get(category) : null;
    }
}
