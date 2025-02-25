package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // ✅ Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // ✅ Get a course by ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // ✅ Sorting: Get courses sorted by name
    public List<Course> getCoursesSorted() {
        return courseRepository.findAllByOrderByNameAsc();
    }

    // ✅ Pagination: Get paginated list of courses
    public Page<Course> getCoursesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable);
    }

    // ✅ JPQL: Get courses by language (case insensitive)
    public List<Course> getCoursesByLanguage(String language) {
        return courseRepository.findByLanguage(language);
    }

    // ✅ JPQL: Get courses by level (case insensitive)
    public List<Course> getCoursesByLevel(String level) {
        return courseRepository.findByLevel(level);
    }

    // ✅ JPQL: Search courses by name (case insensitive)
    public List<Course> searchCoursesByName(String name) {
        return courseRepository.searchByName(name);
    }

    // ✅ Save a new course
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // ✅ Delete a course by ID
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
