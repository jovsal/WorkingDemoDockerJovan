package com.example.controller;


import com.example.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@EnableCircuitBreaker
@RestController
@RequestMapping(value ="/client")
public class LibraryClientController {

    private static Logger LOGGER = LoggerFactory.getLogger(LibraryClientController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/one/{isbn}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("isbn") String isbn){
        ResponseEntity<Book> bookResponse = this.restTemplate.exchange("http://library-service/api/books/one/" + isbn,
                HttpMethod.GET, null, new ParameterizedTypeReference<Book>() {});
        Book book = bookResponse.getBody();
       return book;
    }

    public Iterable<Book> getAllFallback(){
        return Collections.EMPTY_LIST;
    }

    @HystrixCommand(fallbackMethod = "getAllFallback")
    @RequestMapping(value = "/all",method = RequestMethod.GET )
    public Iterable <Book> getAllBooks(){

        ResponseEntity<List<Book>> bookResponse =
                restTemplate.exchange("http://library-service/api/books/all",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {
                        });
        List<Book> books = bookResponse.getBody();
        return books;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Book createBook(@RequestBody Book book){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(book,headers);

        ResponseEntity<Book> responseBook = restTemplate.exchange("http://library-service/api/books/add", HttpMethod.POST,
                entity, Book.class);
        return responseBook.getBody();
    }

    @RequestMapping(value = "/delete/{isbn}", method = RequestMethod.DELETE)
    public Book deleteBook(@PathVariable String isbn){
        LOGGER.info("isbn {}", isbn);
        //ResponseEntity<Book> responseBook = restTemplate.delete("http://server/api/books/delete/{isbn}",isbn, Book.class);
        ResponseEntity<Book> responseBook = restTemplate.exchange("http://library-service/api/books/delete/{isbn}",HttpMethod.DELETE,
                null,  Book.class, isbn);
        return responseBook.getBody();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(book,headers);

        ResponseEntity<Book> responseBook = restTemplate.exchange("http://library-service/api/books/update", HttpMethod.PUT,
                entity, Book.class);
        return responseBook.getBody();
    }

}
