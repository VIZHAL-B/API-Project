package com.example.demo.repository;

import com.example.demo.model.LearningGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningGoalRepository extends JpaRepository<LearningGoal, Long> {
    // Custom query methods if necessary
}
