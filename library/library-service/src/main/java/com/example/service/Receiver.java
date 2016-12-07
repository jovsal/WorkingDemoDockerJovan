package com.example.service;

import com.example.model.Book;
import com.example.util.JsonMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    private static Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private BookService bookService;


    public void receiveMessage(Message message, Channel channel) throws IOException {
        Book book = JsonMapper.nonDefaultMapper().fromJson(new String(message.getBody()), Book.class);
        System.out.println("Received <" + book + message.getMessageProperties().getReceivedRoutingKey() + ">");
        logger.info(String.format("Received message <%s>", new String(message.getBody())));
        logger.info("Routing key:  "+message.getMessageProperties().getReceivedRoutingKey());
        try{

            logger.info("I don't know what to do with this object!!!");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    public void create(Message message, Channel channel) throws IOException {
        Book book = JsonMapper.nonDefaultMapper().fromJson(new String(message.getBody()), Book.class);
        System.out.println("Received <" + book + message.getMessageProperties().getReceivedRoutingKey() + ">");
        logger.info(String.format("Received message <%s>", new String(message.getBody())));
        logger.info("Routing key:  "+message.getMessageProperties().getReceivedRoutingKey());
        try{
            bookService.createBook(book);
            logger.info("Book created");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    public void update(Message message, Channel channel) throws IOException {
        Book book = JsonMapper.nonDefaultMapper().fromJson(new String(message.getBody()), Book.class);
        System.out.println("Received <" + book + message.getMessageProperties().getReceivedRoutingKey() + ">");
        logger.info(String.format("Received message <%s>", new String(message.getBody())));
        logger.info("Routing key:  "+message.getMessageProperties().getReceivedRoutingKey());
        try{
            bookService.updateBook(book);
            logger.info("Book updated");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    public void delete(Message message, Channel channel) throws IOException {
        String isbnToDelete = new String(message.getBody());
        System.out.println("Received <" + isbnToDelete + message.getMessageProperties().getReceivedRoutingKey() + ">");
        logger.info(String.format("Received message <%s>", new String(message.getBody())));
        logger.info("Routing key:  "+message.getMessageProperties().getReceivedRoutingKey());
        try{
            bookService.deleteBookByIsbn(isbnToDelete);
            logger.info("Book deleted");
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e){
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }


}
