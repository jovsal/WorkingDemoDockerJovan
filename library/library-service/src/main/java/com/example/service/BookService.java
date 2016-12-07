package com.example.service;

import com.example.dao.BookRepository;
import com.example.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by bsimonovski on 18-Nov-16.
 */

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.getBookByIsbn(isbn);
    }

    public Book createBook(Book book) {

        return bookRepository.save(book);
    }

//    public void printAllBooks(){
//        Iterable<Book> books = bookRepository.findAll();
//        for(Book b: books){
//            LOGGER.warn(b.toString());
//        }
//    }

    public Book deleteBookByIsbn(String isbn) {
        Book book = getBookByIsbn(isbn);
        if(book != null){
            bookRepository.delete(book);
            return book;
        }
        return null;
    }

    public Book updateBook(Book b){
       return bookRepository.save(b);
    }

}
