package com.example.controller;

import com.example.controller.BookController;
import com.example.model.Book;
import com.example.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bspasovska on 11/22/2016.
 */
public class BookControllerUnitTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getBookUnitTest(){
        Book book = new Book("44", "title5","author5", 5555);

        Mockito.when(bookService.getBookByIsbn("44")).thenReturn(book);

        Book controllerBook = bookController.getBook(book.getIsbn());
        Assert.assertEquals(book, controllerBook);
    }

    @Test
    public void getAllBooksUnitTest(){
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("44", "title5","author5", 5555);
        Book book2 = new Book("55", "title5","author5", 5555);
        books.add(book1);
        books.add(book2);

        Mockito.when(bookService.getAllBooks()).thenReturn(books);

        List<Book> controllerBook = (List<Book>) bookController.getAllBooks();
        Assert.assertEquals(books, controllerBook);
    }



    @Test
    public void createBookUnitTest(){
        Book book = new Book("44", "title5","author5", 5555);
        Mockito.when(bookService.createBook(book)).thenReturn(book);
        Book controllerBook =  bookController.createBook(book);
        Assert.assertEquals(book, controllerBook);
    }

    @Test
    public void updateBookUnitTest(){
        Book book = new Book("44", "title5","author5", 5555);
        Mockito.when(bookService.updateBook(book)).thenReturn(book);
        Book controllerBook =  bookController.updateBook(book);
        Assert.assertEquals(book, controllerBook);
    }

    @Test
    public void deleteBookUnitTest(){
        Book book = new Book("44", "title5","author5", 5555);
        Mockito.when(bookService.deleteBookByIsbn(book.getIsbn())).thenReturn(book);
        Book controllerBook =  bookController.deleteBook(book.getIsbn());
        Assert.assertEquals(book, controllerBook);
    }



}
