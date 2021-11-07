package com.krukovska.cassandra.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class Book {

    private UUID id;
    private String title;
    private String author;
    private String subject;

    public Book(UUID id, String title, String author, String subject) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.subject = subject;
    }

}