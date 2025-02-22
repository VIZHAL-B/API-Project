package com.example.demo.service;

import com.example.demo.model.Language;
import com.example.demo.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    public Optional<Language> getLanguageById(Long id) {
        return languageRepository.findById(id);
    }

    public Language getLanguageByCode(String code) {
        return languageRepository.findByCode(code);
    }

    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    public void deleteLanguage(Long id) {
        languageRepository.deleteById(id);
    }
}
