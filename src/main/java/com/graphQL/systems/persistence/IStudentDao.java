package com.graphQL.systems.persistence;

import com.graphQL.systems.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentDao extends CrudRepository<Student, Long> {
}
