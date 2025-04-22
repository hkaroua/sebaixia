package com.example.productapi.service;

import com.example.productapi.model.DetailLine;
import com.example.productapi.repository.DetailLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailLineService {

    @Autowired
    private DetailLineRepository detailLineRepository;

    public DetailLine save(DetailLine detailLine) {
        return detailLineRepository.save(detailLine);
    }

    public DetailLine findById(Long id) {
        return detailLineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DetailLine not found with id: " + id));
    }

    public List<DetailLine> findAll() {
        return detailLineRepository.findAll();
    }

    public DetailLine update(DetailLine detailLine) {
        findById(detailLine.getId()); // Verify existence
        return detailLineRepository.save(detailLine);
    }

    public void deleteById(Long id) {
        findById(id); // Verify existence
        detailLineRepository.deleteById(id);
    }
}