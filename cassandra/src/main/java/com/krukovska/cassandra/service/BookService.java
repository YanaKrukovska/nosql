package com.krukovska.cassandra.service;

import com.krukovska.cassandra.model.Book;

public interface BookService {

    void createBookTable();

    void saveBook(Book book);

}
