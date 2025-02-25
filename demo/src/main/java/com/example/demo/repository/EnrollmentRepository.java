package com.example.demo.repository;

import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // ✅ Find enrollments by user
    List<Enrollment> findByUser(User user);

    // ✅ Find enrollments by course
    List<Enrollment> findByCourse(Course course);

    // ✅ Sorting: Find all enrollments sorted by date
    List<Enrollment> findAllByOrderByEnrollmentDateAsc();

    // ✅ Pagination: Fetch enrollments with pagination
    Page<Enrollment> findAll(Pageable pageable);

    // ✅ JPQL: Search enrollments by student name (case insensitive)
    @Query("SELECT e FROM Enrollment e WHERE LOWER(e.user.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Enrollment> searchByStudentName(String name);
}
