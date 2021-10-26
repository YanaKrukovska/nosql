package com.krukovska.mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Subtask {

    @Id
    private String id;
    private String name;
    private String description;

    public Subtask(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
