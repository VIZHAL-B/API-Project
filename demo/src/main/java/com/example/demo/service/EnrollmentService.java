package com.example.demo.service;

import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.model.Course;
import com.example.demo.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    // ✅ Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // ✅ Get enrollment by ID
    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    // ✅ Get enrollments by user
    public List<Enrollment> getEnrollmentsByUser(User user) {
        return enrollmentRepository.findByUser(user);
    }

    // ✅ Get enrollments by course
    public List<Enrollment> getEnrollmentsByCourse(Course course) {
        return enrollmentRepository.findByCourse(course);
    }

    // ✅ Save (Create/Update) enrollment
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // ✅ Delete enrollment by ID
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }

    // ✅ Sorting: Get enrollments sorted by date
    public List<Enrollment> getEnrollmentsSorted() {
        return enrollmentRepository.findAllByOrderByEnrollmentDateAsc();
    }

    // ✅ Pagination: Get enrollments with pagination
    public Page<Enrollment> getEnrollmentsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return enrollmentRepository.findAll(pageable);
    }

    // ✅ JPQL: Search enrollments by student name
    public List<Enrollment> searchEnrollmentsByStudentName(String name) {
        return enrollmentRepository.searchByStudentName(name);
    }
}
