package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByOrderByTitleAsc();

    @Query("SELECT c FROM Course c WHERE LOWER(c.language) = LOWER(:language)")
    List<Course> findByLanguage(String language);

    @Query("SELECT c FROM Course c WHERE LOWER(c.level) = LOWER(:level)")
    List<Course> findByLevel(String level);

    @Query("SELECT c FROM Course c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Course> searchByTitle(String title);
}
