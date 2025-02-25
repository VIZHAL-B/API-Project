package com.example.demo.repository;

import com.example.demo.model.LearningGoal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LearningGoalRepository extends JpaRepository<LearningGoal, Long> {

    // ✅ Fetch learning goals before a given target completion date
    List<LearningGoal> findByTargetCompletionDateBefore(LocalDate date);

    // ✅ Fetch learning goals within a given ID range
    List<LearningGoal> findByIdBetween(Long startId, Long endId);

    // ✅ JPQL Query: Find learning goals by title (case insensitive)
    @Query("SELECT lg FROM LearningGoal lg WHERE LOWER(lg.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<LearningGoal> findByTitleContainingIgnoreCase(@Param("title") String title);

    // ✅ JPQL Query: Count total learning goals for a specific user
    @Query("SELECT COUNT(lg) FROM LearningGoal lg WHERE lg.user.id = :userId")
    long countLearningGoalsByUser(@Param("userId") Long userId);

    // ✅ Fetch all learning goals with pagination & sorting
    Page<LearningGoal> findAll(Pageable pageable);
}
