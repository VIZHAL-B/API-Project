package com.example.demo.service;

import com.example.demo.model.Language;
import com.example.demo.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // ✅ Get all languages
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    // ✅ Get language by ID
    public Optional<Language> getLanguageById(Long id) {
        return languageRepository.findById(id);
    }

    // ✅ Get language by code
    public Language getLanguageByCode(String code) {
        return languageRepository.findByCode(code).orElse(null);
    }

    // ✅ Save a new language
    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    // ✅ Delete language by ID
    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }

    // ✅ Pagination & Sorting
    public Page<Language> getLanguagesSortedAndPaginated(int page, int size, String sortBy, String order) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return languageRepository.findAll(pageable);
    }

    // ✅ Search by name (case insensitive)
    public List<Language> searchLanguagesByName(String name) {
        return languageRepository.searchByName(name);
    }
}
