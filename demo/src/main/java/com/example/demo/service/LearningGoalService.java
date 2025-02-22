package com.example.demo.service;

import com.example.demo.model.LearningGoal;
import com.example.demo.repository.LearningGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningGoalService {
    private final LearningGoalRepository learningGoalRepository;

    @Autowired
    public LearningGoalService(LearningGoalRepository learningGoalRepository) {
        this.learningGoalRepository = learningGoalRepository;
    }

    public List<LearningGoal> getAllLearningGoals() {
        return learningGoalRepository.findAll();
    }

    public Optional<LearningGoal> getLearningGoalById(Long id) {
        return learningGoalRepository.findById(id);
    }

    public LearningGoal saveLearningGoal(LearningGoal learningGoal) {
        return learningGoalRepository.save(learningGoal);
    }

    public void deleteLearningGoal(Long id) {
        learningGoalRepository.deleteById(id);
    }
}
