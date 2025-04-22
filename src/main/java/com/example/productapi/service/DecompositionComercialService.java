package com.example.productapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productapi.model.DecompositionComercial;
import com.example.productapi.repository.DecompositionComercialRepository;

import java.util.Optional;

@Service
public class DecompositionComercialService {
    @Autowired
    private DecompositionComercialRepository DecompositionComercialRepository;

    public DecompositionComercial createDecompositionComercial(DecompositionComercial DecompositionComercial) {
        return DecompositionComercialRepository.save(DecompositionComercial);
    }

    public Optional<DecompositionComercial> getDecompositionComercial(Long id) {
        return DecompositionComercialRepository.findById(id);
    }
}
