package com.krukovska.cassandra;

import com.krukovska.cassandra.model.Book;
import com.krukovska.cassandra.service.BookService;
import com.krukovska.cassandra.service.impl.BookServiceImpl;

import java.util.UUID;

public class App {

    public static void main(String[] args) {
        new App().startDemo();
    }

    public void startDemo() {
        CassandraConnector cassandraConnector = new CassandraConnector();
        cassandraConnector.connect("localhost", 9042);
        BookService bookService = new BookServiceImpl(cassandraConnector);
        bookService.createBookTable();
        bookService.saveBook(new Book(UUID.randomUUID(), "Flowers For Algernon", "Daniel Keyes", "Fiction"));
        bookService.saveBook(new Book(UUID.randomUUID(), "The Trial", "Franz Kafka", "Fiction"));
        cassandraConnector.close();
    }
}