package com.example.productapi.model;

public class UserType {
    private String name;
    private String description;

    // Constructeurs
    public UserType() {
    }

    public UserType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
