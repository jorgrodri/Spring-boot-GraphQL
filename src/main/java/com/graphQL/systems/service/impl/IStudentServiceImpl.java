package com.graphQL.systems.service.impl;

import com.graphQL.systems.entities.Student;
import com.graphQL.systems.persistence.IStudentDao;
import com.graphQL.systems.service.IStudentService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class IStudentServiceImpl implements IStudentService {

        @Autowired
        private IStudentDao istudentDao;


    @Override
    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return istudentDao.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return (List<Student>) istudentDao.findAll();
    }

    @Override
    @Transactional
    public Student createStudent(Student student) {
        return istudentDao.save(student);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        istudentDao.deleteById(id);

    }

    @Override
    @Transactional
    public Student updateStudent(Student student) {
        return istudentDao.save(student);
    }
}
