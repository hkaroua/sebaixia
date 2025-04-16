package com.example.productapi.model;

import java.util.List;
import com.example.productapi.model.UserType; // Import UserType if it exists
import com.example.productapi.model.DetailLine; // Import DetailLine if it exists

public class Detail {
    private double unitPrice;
    private List<DetailLine> detailLines;
    private int quantity;
    private double totalPrice;
    private String designation;
    private String cpCpe;
    private UserType userType;

    // Constructeurs
    public Detail() {
    }

    public Detail(double unitPrice, List<DetailLine> detailLines, int quantity, double totalPrice, 
                 String designation, String cpCpe, UserType userType) {
        this.unitPrice = unitPrice;
        this.detailLines = detailLines;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.designation = designation;
        this.cpCpe = cpCpe;
        this.userType = userType;
    }

    // Getters et Setters
    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<DetailLine> getDetailLines() {
        return detailLines;
    }

    public void setDetailLines(List<DetailLine> detailLines) {
        this.detailLines = detailLines;
    }

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCpCpe() {
        return cpCpe;
    }

    public void setCpCpe(String cpCpe) {
        this.cpCpe = cpCpe;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "unitPrice=" + unitPrice +
                ", detailLines=" + detailLines +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", designation='" + designation + '\'' +
                ", cpCpe='" + cpCpe + '\'' +
                ", userType=" + userType +
                '}';
    }
}

