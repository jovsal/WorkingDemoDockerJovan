package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bsimonovski on 18-Nov-16.
 */

@RestController
@RequestMapping(value = "api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @RequestMapping(value="/one/{isbn}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("isbn") String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }


    @RequestMapping(value="/delete/{isbn}", method = RequestMethod.DELETE)
    public Book deleteBook(@PathVariable("isbn") String isbn) {

        return bookService.deleteBookByIsbn(isbn);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book){
       return bookService.updateBook(book);
    }


}
