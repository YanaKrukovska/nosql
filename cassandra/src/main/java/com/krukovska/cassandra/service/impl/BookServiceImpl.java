package com.krukovska.cassandra.service.impl;

import com.krukovska.cassandra.CassandraConnector;
import com.krukovska.cassandra.model.Book;
import com.krukovska.cassandra.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookServiceImpl implements BookService {

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    private static final String CREATE_BOOKS_TABLE = "CREATE TABLE IF NOT EXISTS books_keyspace.books ( id uuid PRIMARY KEY, title text, author text, subject text);";
    private static final String CREATE_KEY_SPACE = "CREATE KEYSPACE IF NOT EXISTS books_keyspace WITH REPLICATION = { 'class' :" +
            " 'NetworkTopologyStrategy', 'datacenter1' : 3 };";
    private static final String INSERT_BOOK = "INSERT INTO books_keyspace.books (id, title, author, subject) VALUES (?, ?, ?, ?)";
    private CassandraConnector client;

    public BookServiceImpl(CassandraConnector cassandraConnector) {
        this.client = cassandraConnector;
    }

    @Override
    public void createBookTable() {
        logger.info("Creating keyspace");
        client.getSession().execute(CREATE_KEY_SPACE);
        logger.info("Creating books table");
        client.getSession().execute(CREATE_BOOKS_TABLE);
    }

    @Override
    public void saveBook(Book book) {
        logger.info("Saving new book {}", book);
        client.getSession().execute(INSERT_BOOK, book.getId(), book.getTitle(), book.getAuthor(), book.getSubject());
    }

}
