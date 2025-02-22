package com.example.demo.controller;

import com.example.demo.model.Language;
import com.example.demo.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageService languageService;

    @Autowired
    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Long id) {
        Optional<Language> language = languageService.getLanguageById(id);
        return language.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Language> getLanguageByCode(@PathVariable String code) {
        Language language = languageService.getLanguageByCode(code);
        if (language != null) {
            return ResponseEntity.ok(language);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Language createLanguage(@RequestBody Language language) {
        return languageService.saveLanguage(language);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }
}
