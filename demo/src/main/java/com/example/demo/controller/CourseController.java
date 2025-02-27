package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ✅ Get all courses with pagination & sorting
    @GetMapping
    public Page<Course> getAllCourses(Pageable pageable) {
        return courseService.getAllCourses(pageable);
    }

    // ✅ Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Get courses by language with pagination & sorting
    @GetMapping("/language")
    public Page<Course> getCoursesByLanguage(@RequestParam String language, Pageable pageable) {
        return courseService.getCoursesByLanguage(language, pageable);
    }

    // ✅ Get courses by level with pagination & sorting
    @GetMapping("/level")
    public Page<Course> getCoursesByLevel(@RequestParam String level, Pageable pageable) {
        return courseService.getCoursesByLevel(level, pageable);
    }

    // ✅ Search courses by title with pagination & sorting
    @GetMapping("/search")
    public Page<Course> searchCoursesByTitle(@RequestParam String title, Pageable pageable) {
        return courseService.searchCoursesByTitle(title, pageable);
    }

    // ✅ Create or update a course
    @PostMapping
    public Course createOrUpdateCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    // ✅ Delete a course by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
