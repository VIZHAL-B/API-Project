package com.example.demo.repository;

import com.example.demo.model.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    
    // ✅ Pagination & Sorting support is provided by JpaRepository
    Page<Enrollment> findAll(Pageable pageable);
}
