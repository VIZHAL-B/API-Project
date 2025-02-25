package com.example.demo.service;

import com.example.demo.model.LearningGoal;
import com.example.demo.model.User;
import com.example.demo.repository.LearningGoalRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LearningGoalService {

    private final LearningGoalRepository learningGoalRepository;
    private final UserRepository userRepository;

    @Autowired
    public LearningGoalService(LearningGoalRepository learningGoalRepository, UserRepository userRepository) {
        this.learningGoalRepository = learningGoalRepository;
        this.userRepository = userRepository;
    }

    // ✅ Fetch all learning goals with pagination & sorting
    public Page<LearningGoal> getAllLearningGoals(Pageable pageable) {
        return learningGoalRepository.findAll(pageable);
    }

    // ✅ Fetch a learning goal by ID
    public Optional<LearningGoal> getLearningGoalById(Long id) {
        return learningGoalRepository.findById(id);
    }

    // ✅ Create a new learning goal with user validation
    @Transactional
    public LearningGoal saveLearningGoal(LearningGoal learningGoal) {
        if (learningGoal.getUser() == null || learningGoal.getUser().getId() == null) {
            throw new IllegalArgumentException("Error: User ID must be provided in the request.");
        }

        User user = userRepository.findById(learningGoal.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Error: User with ID " + learningGoal.getUser().getId() + " does not exist."));

        learningGoal.setUser(user);
        return learningGoalRepository.save(learningGoal);
    }

    // ✅ Update an existing learning goal
    @Transactional
    public Optional<LearningGoal> updateLearningGoal(Long id, LearningGoal learningGoalDetails) {
        return learningGoalRepository.findById(id).map(existingGoal -> {
            existingGoal.setTitle(learningGoalDetails.getTitle());
            existingGoal.setDescription(learningGoalDetails.getDescription());
            existingGoal.setTargetCompletionDate(learningGoalDetails.getTargetCompletionDate());
            return learningGoalRepository.save(existingGoal);
        });
    }

    // ✅ Delete a learning goal
    @Transactional
    public void deleteLearningGoal(Long id) {
        learningGoalRepository.deleteById(id);
    }

    // ✅ Fetch learning goals within a given ID range
    public List<LearningGoal> getLearningGoalsBetweenIds(Long startId, Long endId) {
        return learningGoalRepository.findByIdBetween(startId, endId);
    }

    // ✅ Fetch learning goals with a target completion date before a certain date
    public List<LearningGoal> getLearningGoalsBeforeTargetDate(LocalDate date) {
        return learningGoalRepository.findByTargetCompletionDateBefore(date);
    }

    // ✅ JPQL Query: Find learning goals by title (case insensitive)
    public List<LearningGoal> searchByTitle(String title) {
        return learningGoalRepository.findByTitleContainingIgnoreCase(title);
    }

    // ✅ JPQL Query: Count learning goals for a specific user
    public long countLearningGoalsByUser(Long userId) {
        return learningGoalRepository.countLearningGoalsByUser(userId);
    }
}