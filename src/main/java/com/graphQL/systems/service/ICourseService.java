package com.graphQL.systems.service;

import com.graphQL.systems.entities.Course;

import java.util.List;

public interface ICourseService {

    Course findById(Long id);

    List<Course> findAll();

    Course createCourse(Course course);

    void deleteCourse(Long id);

    Course updateCourse(Course course);
}
