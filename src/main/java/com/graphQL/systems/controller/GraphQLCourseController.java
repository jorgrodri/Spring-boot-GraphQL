package com.graphQL.systems.controller;

import com.graphQL.systems.entities.Course;
import com.graphQL.systems.graphql.InputCourse;
import com.graphQL.systems.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLCourseController {

    @Autowired
    private ICourseService courseService;


    @QueryMapping(name = "findCourseById")
    public Course findById(@Argument(name = "courseId") String id) {
        long idLong = Long.parseLong(id);
        return courseService.findById(idLong);

    }

    @QueryMapping(name = "findAllCourses")
    public List<Course> findAll() {

        return courseService.findAll();
    }

    @MutationMapping
    public Course createCourse(@Argument InputCourse inputCourse) {
        Course course = new Course();
        course.setName(inputCourse.getName());
        course.setCategory(inputCourse.getCategory());
        course.setTeacher(inputCourse.getTeacher());
        courseService.createCourse(course);
        return course;
    }

    @MutationMapping(name = "updateCourseById")
    public Course updateCourse( @Argument InputCourse inputCourse, @Argument(name = "courseId") String id) {
        Course course = courseService.findById(Long.parseLong(id));


        if (course != null) {
            if (inputCourse.getName() != null) course.setName(inputCourse.getName());
            if (inputCourse.getCategory() != null) course.setCategory(inputCourse.getCategory());
            if (inputCourse.getTeacher() != null) course.setTeacher(inputCourse.getTeacher());
        }
        courseService.updateCourse(course);

        return course;
    }

    @MutationMapping(name = "deleteCourseById")
    public String deleteCourse(@Argument(name = "courseId") String id) {
        long idLong = Long.parseLong(id);
        courseService.deleteCourse(idLong);
        return "Deleted course with id " + id;
    }
}

