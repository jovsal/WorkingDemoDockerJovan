package com.example.dao;

import com.example.model.Book;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    Book mockBook = new Book("1", "Book Title 1","author name 1",2016);

    Book bookListOne = new Book("2", "Book Title 2","author name 2",2016);
    Book bookListTwo = new Book("3", "Book Title 3", "author name 3",2016);


    @Test
    public void saveBook()
    {
        bookRepository.deleteAll();
        Book saved = bookRepository.save(mockBook);
        assertEquals(saved, mockBook);
    }

    @Test
    public void getBookByIsbn() throws Exception {
        bookRepository.deleteAll();
        Book saved = bookRepository.save(mockBook);
        Book found = bookRepository.getBookByIsbn(saved.getIsbn());

        assertEquals(saved,found);
    }

    @Test
    public void saveAndGetBookByIsbn()
    {
        bookRepository.deleteAll();

        Book saved = bookRepository.save(mockBook);
        Book bookReceived = bookRepository.getBookByIsbn("1");

        assertEquals(saved,bookReceived);
    }

    @Test
    public void getAllBooks()
    {
        bookRepository.deleteAll();

        bookRepository.save(bookListOne);
        bookRepository.save(bookListTwo);

        Iterable<Book> returnedBooks = bookRepository.findAll();
        List<Book> returnList = Lists.newArrayList(returnedBooks);

        assertEquals(returnList.get(1),bookListOne);
        assertEquals(returnList.get(0),bookListTwo);
    }

    @Test
    public void deleteOneBook()
    {
        bookRepository.deleteAll();
        bookRepository.save(mockBook);

        bookRepository.delete(mockBook);
        Book bookByIsbn = bookRepository.getBookByIsbn(mockBook.getIsbn());

        assertEquals(bookByIsbn,null);
    }






}
