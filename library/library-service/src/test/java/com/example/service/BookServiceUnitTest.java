package com.example.service;

import com.example.dao.BookRepository;
import com.example.model.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

/**
 * Created by bsimonovski on 22-Nov-16.
 */
public class BookServiceUnitTest {
    @InjectMocks
    private BookService bookService;


    @Mock
    private BookRepository bookRepository;

    @Before
    public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAllBooksUnitTest() throws Exception {
        List<Book> books = new ArrayList<>();
        Book book1 = new Book("1a","title1","author1",2016);
        Book book2 = new Book("2b","title1","author1",2016);
        books.add(book1);
        books.add(book2);

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        List<Book> serviceBooks = (List<Book>) bookService.getAllBooks();
        Assert.assertEquals(books, serviceBooks);
    }

    @Test
    public void getBookByIsbnUnitTest() throws Exception {
       Book book = new Book("1a","title1","author1",2016);
        Mockito.when(bookRepository.getBookByIsbn("1a")).thenReturn(book);
        Book serviceBook = bookService.getBookByIsbn(book.getIsbn());
        Assert.assertEquals(book,serviceBook );
    }

    @Test
    public void createBookUnitTest() throws Exception {
        Book book = new Book("1a","title1","author1",2016);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Book serviceBook = bookService.createBook(book);
        Assert.assertEquals(book,serviceBook);
    }

    @Test
    public void deleteBookByIsbnUnitTest() throws Exception {
        Book book = new Book("1a","title1","author1",2016);
        Mockito.when(bookRepository.getBookByIsbn(book.getIsbn())).thenReturn(book);
        bookService.deleteBookByIsbn(book.getIsbn());
        Mockito.verify(bookRepository, times(1)).delete(book);
    }

    @Test
    public void updateBookUnitTest() throws Exception {
        Book book = new Book("1a","title1","author1",2016);
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Book serviceBook =  bookService.updateBook(book);
        Assert.assertEquals(book, serviceBook);
    }

}