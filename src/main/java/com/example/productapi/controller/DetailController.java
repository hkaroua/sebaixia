package com.example.productapi.controller;

import com.example.productapi.model.Detail;
import com.example.productapi.service.DetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Tag(name = "Detail API", description = "Endpoints for managing details")
@RestController
@RequestMapping("/api/details")
public class DetailController {

    @Autowired
    private DetailService detailService;

    @Operation(summary = "Create a detail", description = "Adds a new detail for a product")
    @ApiResponse(responseCode = "201", description = "Detail created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @PostMapping
    public ResponseEntity<Detail> createDetail(@Valid @RequestBody Detail detail) {
        return ResponseEntity.ok(detailService.createDetail(detail));
    }

    @Operation(summary = "Get a detail", description = "Retrieves a detail by its ID")
    @ApiResponse(responseCode = "200", description = "Detail found")
    @ApiResponse(responseCode = "404", description = "Detail not found")
    @GetMapping("/{id}")
    public ResponseEntity<Detail> getDetail(@PathVariable Long id) {
        return ResponseEntity.ok(detailService.getDetail(id));
    }

    @Operation(summary = "Get all details", description = "Retrieves all details")
    @ApiResponse(responseCode = "200", description = "List of details retrieved successfully")
    @GetMapping
    public ResponseEntity<List<Detail>> getAllDetails() {
        return ResponseEntity.ok(detailService.getAllDetails());
    }

    @Operation(summary = "Update a detail", description = "Updates an existing detail")
    @ApiResponse(responseCode = "200", description = "Detail updated successfully")
    @ApiResponse(responseCode = "404", description = "Detail not found")
    @PutMapping("/{id}")
    public ResponseEntity<Detail> updateDetail(@PathVariable Long id, @Valid @RequestBody Detail detailDetails) {
        return ResponseEntity.ok(detailService.updateDetail(id, detailDetails));
    }

    @Operation(summary = "Delete a detail", description = "Deletes a detail")
    @ApiResponse(responseCode = "204", description = "Detail deleted successfully")
    @ApiResponse(responseCode = "404", description = "Detail not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetail(@PathVariable Long id) {
        detailService.deleteDetail(id);
        return ResponseEntity.noContent().build();
    }
}
