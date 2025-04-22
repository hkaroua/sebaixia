package com.example.productapi.service;

import com.example.productapi.model.Detail;
import com.example.productapi.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetailService {

    @Autowired
    private DetailRepository detailRepository;

    public Detail createDetail(Detail detail) {
        return detailRepository.save(detail);
    }

    public Detail getDetail(Long id) {
        return detailRepository.findById(id)
            .orElseThrow();
    }

    public List<Detail> getAllDetails() {
        return detailRepository.findAll();
    }

    public Detail updateDetail(Long id, Detail detailDetails) {
        Detail detail = getDetail(id);
        detail.setUnitPrice(detailDetails.getUnitPrice());
        detail.setDetailLines(detailDetails.getDetailLines());
        detail.setQuantity(detailDetails.getQuantity());
        detail.setTotalPrice(detailDetails.getTotalPrice());
        detail.setDesignation(detailDetails.getDesignation());
        detail.setCpCpe(detailDetails.getCpCpe());
        detail.setUserType(detailDetails.getUserType());
        detail.setDecompositionComercial(detailDetails.getDecompositionComercial());
        return detailRepository.save(detail);
    }

    public void deleteDetail(Long id) {
        Detail detail = getDetail(id);
        detailRepository.delete(detail);
    }
}