package com.graphQL.systems.service;

import com.graphQL.systems.entities.Student;

import java.util.List;

public interface IStudentService {


    Student findById(Long id);

    List<Student> findAll();

    Student createStudent(Student student);

    void deleteStudent(Long id);

    Student updateStudent(Student student);
}
