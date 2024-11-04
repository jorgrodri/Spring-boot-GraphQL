package com.graphQL.systems.controller;

import com.graphQL.systems.entities.Course;
import com.graphQL.systems.entities.Student;
import com.graphQL.systems.graphql.InputStudent;
import com.graphQL.systems.service.ICourseService;
import com.graphQL.systems.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLStudentController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ICourseService courseService;

    @QueryMapping(name = "findStudentById")
    public Student findById(@Argument(name = "studentId") String id) {
        long idLong = Long.parseLong(id);

        return studentService.findById(idLong);
    }


    @QueryMapping(name = "findAllStudents")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @MutationMapping(name = "createStudent")
    public Student createStudent(@Argument InputStudent inputStudent) {
        Course course = courseService.findById(Long.parseLong(inputStudent.getCourseId()));

        Student student = new Student();
        student.setName(inputStudent.getName());
        student.setLastName(inputStudent.getLastName());
        student.setAge(inputStudent.getAge());
        student.setCourse(course);

        studentService.createStudent(student);

        return student;
    }

    @MutationMapping(name = "deleteStudentById")
    public String deleteStudent(@Argument(name = "studentId") String id) {
        long idLong = Long.parseLong(id);
        studentService.deleteStudent(idLong);
        return "Deleted student with id " + id;
    }

    @MutationMapping(name = "updateStudentById")
    public Student updateStudent(@Argument InputStudent inputStudent, @Argument(name = "studentId") String id) {
        // Buscar al estudiante en la base de datos
        Student student = studentService.findById(Long.parseLong(id));

        if (student != null) {
            // Actualizar solo los campos que no son nulos en inputStudent
            if (inputStudent.getName() != null) {
                student.setName(inputStudent.getName());
            }
            if (inputStudent.getLastName() != null) {
                student.setLastName(inputStudent.getLastName());
            }
            if (inputStudent.getAge() != null) {
                student.setAge(inputStudent.getAge());
            }
            if (inputStudent.getCourseId() != null) {
                Course course = courseService.findById(Long.parseLong(inputStudent.getCourseId()));
                if (course != null) {
                    student.setCourse(course);
                } else {
                    throw new RuntimeException("Course not found");
                }
            }

            // Guardar el estudiante actualizado
            studentService.updateStudent(student);
            return student; // Retornar el objeto Student actualizado
        } else {
            throw new RuntimeException("Student not found");
        }
    }
    /*@MutationMapping(name = "updateStudentById")
    public String updateStudent(@Argument InputStudent inputStudent, @Argument(name = "studentId") String id) {
        Student student = studentService.findById(Long.parseLong(id));
        if (student != null) {
            if (inputStudent.getName() != null) student.setName(inputStudent.getName());
            if (inputStudent.getLastName() != null) student.setLastName(inputStudent.getLastName());
            if (inputStudent.getAge() != null) student.setAge(inputStudent.getAge());
           if (inputStudent.getCourseId() != null) {
               Course course = courseService.findById(Long.parseLong(inputStudent.getCourseId()));
               student.setCourse(course);
           }

        }
        studentService.updateStudent(student);
        return "Updated student with id " + id;
    }*/
}


