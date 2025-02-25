package com.example.demo.controller;

import com.example.demo.model.Language;
import com.example.demo.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return language != null ? ResponseEntity.ok(language) : ResponseEntity.notFound().build();
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

    // ✅ **Sorting**
    @GetMapping("/sorted")
    public List<Language> getLanguagesSorted() {
        return languageService.getLanguagesSorted();
    }

    // ✅ **Pagination**
    @GetMapping("/paginated")
    public Page<Language> getLanguagesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return languageService.getLanguagesPaginated(page, size);
    }

    // ✅ **JPQL Search by Name**
    @GetMapping("/search")
    public List<Language> searchLanguagesByName(@RequestParam String name) {
        return languageService.searchLanguagesByName(name);
    }
}
