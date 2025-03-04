package com.example.demo.repository;

import com.example.demo.model.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    // ✅ Pagination and Sorting (built-in support from JpaRepository)
    Page<Language> findAll(Pageable pageable);

    // ✅ Custom Query to find a language by code
    @Query("SELECT l FROM Language l WHERE l.code = :code")
    Optional<Language> findByCode(String code);

    // ✅ Search by name (case insensitive)
    @Query("SELECT l FROM Language l WHERE LOWER(l.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Language> searchByName(String name);
}
