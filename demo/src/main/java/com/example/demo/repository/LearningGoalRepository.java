package com.example.demo.repository;

import com.example.demo.model.LearningGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LearningGoalRepository extends JpaRepository<LearningGoal, Long> {

    // ✅ Fetch learning goals within a given ID range
    List<LearningGoal> findByIdBetween(Long startId, Long endId);

    // ✅ Fetch learning goals before a specific target completion date
    List<LearningGoal> findByTargetCompletionDateBefore(LocalDate date);

    // ✅ JPQL Query: Search learning goals by title (case insensitive)
    @Query("SELECT lg FROM LearningGoal lg WHERE LOWER(lg.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<LearningGoal> searchByTitle(String title);

    // ✅ JPQL Query: Count learning goals for a specific user
    @Query("SELECT COUNT(lg) FROM LearningGoal lg WHERE lg.user.id = :userId")
    long countLearningGoalsByUser(Long userId);
}
