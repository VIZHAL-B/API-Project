package com.example.demo.service;

import com.example.demo.model.Language;
import com.example.demo.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    // ✅ Retrieve all languages
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    // ✅ Get a language by ID
    public Optional<Language> getLanguageById(Long id) {
        return languageRepository.findById(id);
    }

    // ✅ Get a language by code
    public Language getLanguageByCode(String code) {
        return languageRepository.findByCode(code).orElse(null);
    }

    // ✅ Save a language
    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    // ✅ Delete a language by ID
    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }

    // ✅ Sorting: Retrieve sorted list of languages
    public List<Language> getLanguagesSorted() {
        return languageRepository.findAllByOrderByNameAsc();
    }

    // ✅ Pagination: Retrieve paginated list of languages
    public Page<Language> getLanguagesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return languageRepository.findAll(pageable);
    }

    // ✅ JPQL: Search languages by name (case insensitive)
    public List<Language> searchLanguagesByName(String name) {
        return languageRepository.searchByName(name);
    }
}
