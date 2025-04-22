package com.example.productapi.controller;

import com.example.productapi.model.DetailLine;
import com.example.productapi.service.DetailLineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "DetailLine API")
@RestController
@RequestMapping("/api/detail-lines")
public class DetailLineController {

    @Autowired
    private DetailLineService detailLineService;

    @Operation(summary = "Create a detail line", description = "Adds a new detail line")
    @ApiResponse(responseCode = "201", description = "Detail line created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @PostMapping
    public DetailLine createDetailLine(@RequestBody DetailLine detailLine) {
        return detailLineService.save(detailLine);
    }

    @Operation(summary = "Get a detail line", description = "Retrieves a detail line by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<DetailLine> getDetailLine(@PathVariable Long id) {
        return ResponseEntity.ok(detailLineService.findById(id));
    }

    @Operation(summary = "Get all detail lines", description = "Retrieves all detail lines")
    @GetMapping
    public List<DetailLine> getAllDetailLines() {
        return detailLineService.findAll();
    }

    @Operation(summary = "Update a detail line", description = "Updates an existing detail line")
    @PutMapping("/{id}")
    public ResponseEntity<DetailLine> updateDetailLine(@PathVariable Long id, @RequestBody DetailLine detailLine) {
        detailLine.setId(id);
        return ResponseEntity.ok(detailLineService.update(detailLine));
    }

    @Operation(summary = "Delete a detail line", description = "Deletes a detail line by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailLine(@PathVariable Long id) {
        detailLineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}