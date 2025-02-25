package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // ✅ Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // ✅ Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Sorting: Get all courses sorted by name
    @GetMapping("/sorted")
    public List<Course> getAllSortedCourses() {
        return courseService.getCoursesSorted();
    }

    // ✅ Pagination: Get paginated list of courses
    @GetMapping("/paginated")
    public Page<Course> getCoursesPaginated(@RequestParam int page, @RequestParam int size) {
        return courseService.getCoursesPaginated(page, size);
    }

    // ✅ JPQL: Get courses by language (case insensitive)
    @GetMapping("/language/{language}")
    public List<Course> getCoursesByLanguage(@PathVariable String language) {
        return courseService.getCoursesByLanguage(language);
    }

    // ✅ JPQL: Get courses by level (case insensitive)
    @GetMapping("/level/{level}")
    public List<Course> getCoursesByLevel(@PathVariable String level) {
        return courseService.getCoursesByLevel(level);
    }

    // ✅ Search courses by name (case insensitive)
    @GetMapping("/search")
    public List<Course> searchCoursesByName(@RequestParam String name) {
        return courseService.searchCoursesByName(name);
    }

    // ✅ Create a new course
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    // ✅ Delete a course by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
