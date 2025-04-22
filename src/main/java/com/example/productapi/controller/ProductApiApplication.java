package com.example.productapi.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.productapi.model.UserType;
import com.example.productapi.repository.UserTypeRepository;

@SpringBootApplication
public class ProductApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserTypeRepository userTypeRepository) {
        return (args) -> {
            // Create some sample user types
            UserType adminType = new UserType("ADMIN", "Administrator user type");
            UserType userType = new UserType("USER", "Standard user type");
            UserType guestType = new UserType("GUEST", "Guest user type");

            // Save them to the database
            System.out.println("Saving user types...");
            userTypeRepository.save(adminType);
            userTypeRepository.save(userType);
            userTypeRepository.save(guestType);

            // Find all user types
            System.out.println("\nAll user types:");
            userTypeRepository.findAll().forEach(type -> {
                System.out.println(type.toString());
            });

            // Find user types by name
            System.out.println("\nFinding user type with name 'ADMIN':");
            userTypeRepository.findByName("ADMIN").forEach(type -> {
                System.out.println(type.toString());
            });

            // Update a user type
            System.out.println("\nUpdating GUEST user type...");
            userTypeRepository.findByName("GUEST").stream().findFirst().ifPresent(type -> {
                type.setDescription("Updated guest user type description");
                userTypeRepository.update(type.getId(), type);
            });

            // Show all user types after update
            System.out.println("\nAll user types after update:");
            userTypeRepository.findAll().forEach(type -> {
                System.out.println(type.toString());
            });
        };
    }
}