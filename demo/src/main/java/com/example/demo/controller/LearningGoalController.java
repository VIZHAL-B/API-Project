package com.example.demo.controller;

import com.example.demo.model.LearningGoal;
import com.example.demo.service.LearningGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/learning-goals")
public class LearningGoalController {
    private final LearningGoalService learningGoalService;

    @Autowired
    public LearningGoalController(LearningGoalService learningGoalService) {
        this.learningGoalService = learningGoalService;
    }

    @GetMapping
    public List<LearningGoal> getAllLearningGoals() {
        return learningGoalService.getAllLearningGoals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningGoal> getLearningGoalById(@PathVariable Long id) {
        Optional<LearningGoal> learningGoal = learningGoalService.getLearningGoalById(id);
        return learningGoal.map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public LearningGoal createLearningGoal(@RequestBody LearningGoal learningGoal) {
        return learningGoalService.saveLearningGoal(learningGoal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearningGoal(@PathVariable Long id) {
        learningGoalService.deleteLearningGoal(id);
        return ResponseEntity.noContent().build();
    }
}
