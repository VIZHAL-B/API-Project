package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // ✅ Sorting: Get all courses sorted by name
    List<Course> findAllByOrderByNameAsc();

    // ✅ Pagination: Get courses with pagination
    Page<Course> findAll(Pageable pageable);

    // ✅ JPQL: Get courses by language (case insensitive)
    @Query("SELECT c FROM Course c WHERE LOWER(c.language) = LOWER(:language)")
    List<Course> findByLanguage(String language);

    // ✅ JPQL: Get courses by level (case insensitive)
    @Query("SELECT c FROM Course c WHERE LOWER(c.level) = LOWER(:level)")
    List<Course> findByLevel(String level);

    // ✅ JPQL: Search courses by name (case insensitive)
    @Query("SELECT c FROM Course c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Course> searchByName(String name);
}
