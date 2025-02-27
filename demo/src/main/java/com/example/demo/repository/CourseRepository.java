package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findAll(Pageable pageable);

    @Query("SELECT c FROM Course c WHERE LOWER(c.language) = LOWER(:language)")
    Page<Course> findByLanguage(String language, Pageable pageable);

    @Query("SELECT c FROM Course c WHERE LOWER(c.level) = LOWER(:level)")
    Page<Course> findByLevel(String level, Pageable pageable);

    @Query("SELECT c FROM Course c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Course> searchByTitle(String title, Pageable pageable);
}
