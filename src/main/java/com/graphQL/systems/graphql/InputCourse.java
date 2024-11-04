package com.graphQL.systems.graphql;

import lombok.Data;

@Data
public class InputCourse {

    private long id;

    private String name;

    private String category;

    private String teacher;
}