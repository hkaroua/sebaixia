package com.example.productapi.controller;

import com.example.productapi.service.DecompositionComercialService;
import com.example.productapi.model.DecompositionComercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/DecompositionComercial")
public class DecompositionComercialController {

    @Autowired
    private DecompositionComercialService decompositionComercialService;

    @PostMapping
    public DecompositionComercial createDecompositionComercial(@RequestBody DecompositionComercial decompositionComercial) {
        return decompositionComercialService.createDecompositionComercial(decompositionComercial);
    }

    @GetMapping("/{id}")
    public Optional<DecompositionComercial> getDecompositionComercial(@PathVariable Long id) {
        return decompositionComercialService.getDecompositionComercial(id);
    }
    //get all 
    //update
    //delete
}