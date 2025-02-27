package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // ✅ Get all courses with pagination & sorting
    public Page<Course> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    // ✅ Get a course by ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // ✅ Get courses by language with pagination & sorting
    public Page<Course> getCoursesByLanguage(String language, Pageable pageable) {
        return courseRepository.findByLanguage(language, pageable);
    }

    // ✅ Get courses by level with pagination & sorting
    public Page<Course> getCoursesByLevel(String level, Pageable pageable) {
        return courseRepository.findByLevel(level, pageable);
    }

    // ✅ Search courses by title with pagination & sorting
    public Page<Course> searchCoursesByTitle(String title, Pageable pageable) {
        return courseRepository.searchByTitle(title, pageable);
    }

    // ✅ Create or update a course
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    // ✅ Delete a course by ID
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
