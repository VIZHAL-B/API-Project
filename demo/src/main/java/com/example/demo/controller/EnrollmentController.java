package com.example.demo.controller;

import com.example.demo.model.Enrollment;
import com.example.demo.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // ✅ Get all enrollments
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // ✅ Get an enrollment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(id);
        return enrollment.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Sorting: Get all enrollments sorted by date
    @GetMapping("/sorted")
    public List<Enrollment> getAllSortedEnrollments() {
        return enrollmentService.getEnrollmentsSorted();
    }

    // ✅ Pagination: Get enrollments with pagination
    @GetMapping("/paginated")
    public Page<Enrollment> getEnrollmentsPaginated(@RequestParam int page, @RequestParam int size) {
        return enrollmentService.getEnrollmentsPaginated(page, size);
    }

    // ✅ JPQL: Search enrollments by student name (case insensitive)
    @GetMapping("/search")
    public List<Enrollment> searchByStudentName(@RequestParam String name) {
        return enrollmentService.searchEnrollmentsByStudentName(name);
    }

    // ✅ Create a new enrollment
    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    // ✅ Delete an enrollment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
