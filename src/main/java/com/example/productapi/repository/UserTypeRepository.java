package com.example.productapi.repository;

import com.example.productapi.model.UserType;
import java.util.List;
import java.util.Optional;

/**
 * Interface pour les opérations CRUD sur les types d'utilisateur
 */
public interface UserTypeRepository {
    UserType save(UserType userType);

    /**
     * Trouve un type d'utilisateur par son ID
     * 
     * @param id L'ID du type d'utilisateur à trouver
     * @return Un Optional contenant le type d'utilisateur s'il existe
     */
    Optional<UserType> findById(Long id);
    List<UserType> findAll();
    List<UserType> findByName(String name);
    UserType update(Long id, UserType userType);

    /**
     * Supprime un type d'utilisateur par son ID
     * 
     * @param id L'ID du type d'utilisateur à supprimer
     * @return true si le type d'utilisateur a été supprimé, false sinon
     */
    boolean deleteById(Long id);
}
