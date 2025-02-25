package com.example.demo.controller;

import com.example.demo.model.LearningGoal;
import com.example.demo.service.LearningGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/learning-goals")
public class LearningGoalController {

    private final LearningGoalService learningGoalService;

    @Autowired
    public LearningGoalController(LearningGoalService learningGoalService) {
        this.learningGoalService = learningGoalService;
    }

    // ✅ Get all learning goals with sorting & pagination
    @GetMapping
    public Page<LearningGoal> getAllLearningGoals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {

        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return learningGoalService.getAllLearningGoals(pageable);
    }

    // ✅ Get learning goal by ID
    @GetMapping("/{id}")
    public ResponseEntity<LearningGoal> getLearningGoalById(@PathVariable Long id) {
        return learningGoalService.getLearningGoalById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Fetch learning goals within a given ID range
    @GetMapping("/range")
    public List<LearningGoal> getLearningGoalsInRange(
            @RequestParam Long startId,
            @RequestParam Long endId) {
        return learningGoalService.getLearningGoalsBetweenIds(startId, endId);
    }

    // ✅ Search learning goals by title (JPQL Query)
    @GetMapping("/search")
    public List<LearningGoal> searchByTitle(@RequestParam String title) {
        return learningGoalService.searchByTitle(title);
    }

    // ✅ Count learning goals for a specific user (JPQL Query)
    @GetMapping("/count")
    public long countLearningGoalsByUser(@RequestParam Long userId) {
        return learningGoalService.countLearningGoalsByUser(userId);
    }

    // ✅ Create a new learning goal
    @PostMapping
    public ResponseEntity<LearningGoal> createLearningGoal(@Validated @RequestBody LearningGoal learningGoal) {
        LearningGoal savedGoal = learningGoalService.saveLearningGoal(learningGoal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGoal);
    }

    // ✅ Update an existing learning goal
    @PutMapping("/{id}")
    public ResponseEntity<LearningGoal> updateLearningGoal(
            @PathVariable Long id,
            @Validated @RequestBody LearningGoal learningGoalDetails) {

        return learningGoalService.updateLearningGoal(id, learningGoalDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Delete a learning goal by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearningGoal(@PathVariable Long id) {
        learningGoalService.deleteLearningGoal(id);
        return ResponseEntity.noContent().build();
    }
}
