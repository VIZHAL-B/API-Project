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

    // ✅ Get all languages
    @GetMapping
    public List<Language> getAllLanguages() {
        return languageService.getAllLanguages();
    }

    // ✅ Get language by ID
    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Long id) {
        Optional<Language> language = languageService.getLanguageById(id);
        return language.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Get language by code
    @GetMapping("/code/{code}")
    public ResponseEntity<Language> getLanguageByCode(@PathVariable String code) {
        Language language = languageService.getLanguageByCode(code);
        return language != null ? ResponseEntity.ok(language) : ResponseEntity.notFound().build();
    }

    // ✅ Create a new language
    @PostMapping
    public Language createLanguage(@RequestBody Language language) {
        return languageService.saveLanguage(language);
    }

    // ✅ Delete language by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id) {
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Pagination & Sorting
    @GetMapping("/sorted-paginated")
    public Page<Language> getLanguagesSortedAndPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return languageService.getLanguagesSortedAndPaginated(page, size, sortBy, order);
    }

    // ✅ Search by name
    @GetMapping("/search")
    public List<Language> searchLanguagesByName(@RequestParam String name) {
        return languageService.searchLanguagesByName(name);
    }
}
