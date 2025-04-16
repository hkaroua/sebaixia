package com.example.productapi.model;

public class DetailLine {
    private double unitPrice;
    private double quantity;
    private double totalPrice;
    private String designation;
    private String detail;
    private String cpe;
    private String cpCpe;
    private UserType userType;

    // Constructeurs
    public DetailLine() {
    }

    public DetailLine(double unitPrice, double quantity, double totalPrice, String designation,
                     String detail, String cpe, String cpCpe, UserType userType) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.designation = designation;
        this.detail = detail;
        this.cpe = cpe;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCpe() {
        return cpe;
    }

    public void setCpe(String cpe) {
        this.cpe = cpe;
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
        return "DetailLine{" +
                "unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", designation='" + designation + '\'' +
                ", detail='" + detail + '\'' +
                ", cpe='" + cpe + '\'' +
                ", cpCpe='" + cpCpe + '\'' +
                ", userType=" + userType +
                '}';
    }
}
