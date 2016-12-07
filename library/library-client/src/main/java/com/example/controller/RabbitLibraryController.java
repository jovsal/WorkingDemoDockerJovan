package com.example.controller;

import com.example.config.MessagingNames;
import com.example.model.Book;
import com.example.util.JsonMapper;
import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/rabbit/producer")
public class RabbitLibraryController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void add(@RequestBody Book book){
        System.out.println("sending MSG......"+book);
        rabbitTemplate.convertAndSend(MessagingNames.exchangeName,MessagingNames.routingKeyCreate, JsonMapper.nonDefaultMapper().toJson(book));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestBody String isbn){
        System.out.println("sending MSG......"+isbn);
        rabbitTemplate.convertAndSend(MessagingNames.exchangeName,MessagingNames.routingKeyDelete, isbn);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Book book){
        System.out.println("sending MSG......"+book);
        rabbitTemplate.convertAndSend(MessagingNames.exchangeName,MessagingNames.routingKeyUpdate, JsonMapper.nonDefaultMapper().toJson(book));
    }

}
