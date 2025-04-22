package com.example.productapi.repository;

import com.example.productapi.model.DecompositionComercial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Interface pour les opérations CRUD sur les entités DecompositionComercial
 */
public interface DecompositionComercialRepository extends JpaRepository<DecompositionComercial, Long> {

    /**
     * Trouve une entité par son ID
     *
     * @param id L'ID à rechercher
     * @return Un Optional contenant l'entité si elle existe
     */
    Optional<DecompositionComercial> findById(Long id);

    /**
     * Trouve toutes les entités
     *
     * @return Une liste de toutes les entités
     */
    List<DecompositionComercial> findAll();

    /**
     * Trouve des entités par code article
     *
     * @param artCode Le code article à rechercher
     * @return Une liste des entités correspondantes
     */
    List<DecompositionComercial> findByArtCode(String artCode);
}
