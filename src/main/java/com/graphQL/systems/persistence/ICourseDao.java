package com.graphQL.systems.persistence;

import com.graphQL.systems.entities.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICourseDao extends CrudRepository  <Course, Long> {
}
