package com.example.dao;

import com.example.model.Book;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Repository;

/**
 * Created by bsimonovski on 18-Nov-16.
 */


public interface BookRepository extends CassandraRepository<Book> {
    @Query("SELECT * FROM library.book WHERE isbn = ?0")
    public Book getBookByIsbn(String id);



}
