package com.example.productapi.model;

import java.util.List;

public class DecompositionComercial {
    private int quantity;
    private double totalPrice;
    private double totalPriceLowCharges;
    private String artCode;
    private List<Detail> details;
    private String productName;

    // Constructeurs
    public DecompositionComercial() {
    }

    public DecompositionComercial(int quantity, double totalPrice, double totalPriceLowCharges, String artCode,
            List<Detail> details, String productName) {
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.totalPriceLowCharges = totalPriceLowCharges;
        this.artCode = artCode;
        this.details = details;
        this.productName = productName;
    }

    // Getters et Setters
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPriceLowCharges() {
        return totalPriceLowCharges;
    }

    public void setTotalPriceLowCharges(double totalPriceLowCharges) {
        this.totalPriceLowCharges = totalPriceLowCharges;
    }

    public String getArtCode() {
        return artCode;
    }

    public void setArtCode(String artCode) {
        this.artCode = artCode;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", totalPriceLowCharges=" + totalPriceLowCharges +
                ", artCode='" + artCode + '\'' +
                ", details=" + details +
                ", productName='" + productName + '\'' +
                '}';
    }
}