package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> getCoursesSorted() {
        return courseRepository.findAllByOrderByTitleAsc();
    }

    public Page<Course> getCoursesPaginated(int page, int size) {
        return courseRepository.findAll(Pageable.ofSize(size).withPage(page));
    }

    public List<Course> getCoursesByLanguage(String language) {
        return courseRepository.findByLanguage(language);
    }

    public List<Course> getCoursesByLevel(String level) {
        return courseRepository.findByLevel(level);
    }

    // ✅ Corrected: Implement the missing method
    public List<Course> searchCoursesByTitle(String title) {
        return courseRepository.searchByTitle(title);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
