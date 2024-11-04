package com.graphQL.systems.service.impl;

import com.graphQL.systems.entities.Course;
import com.graphQL.systems.persistence.ICourseDao;
import com.graphQL.systems.service.ICourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ICourseServiceImpl implements ICourseService {


    @Autowired
    private ICourseDao courseDao;

    @Override
    @Transactional(readOnly = true)
    public Course findById(Long id) {
        return courseDao.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return (List<Course>) courseDao.findAll();
    }

    @Override
    @Transactional
    public Course createCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        courseDao.deleteById(id);

    }

    @Override
    @Transactional
    public Course updateCourse(Course course) {
        return courseDao.save(course);
    }
}
