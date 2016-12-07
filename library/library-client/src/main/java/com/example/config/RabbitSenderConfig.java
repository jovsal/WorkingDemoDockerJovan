package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitSenderConfig {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

//    @Bean
//    Queue queue(){
//        return new Queue(MessagingNames.queueName, false);
//    }

//
//    @Bean
//    TopicExchange exchange(){
//        return new TopicExchange(MessagingNames.exchangeName);
//    }

//    @Bean
//    Binding binding(Queue queue, TopicExchange topicExchange){
//        return BindingBuilder.bind(queue).to(topicExchange).with(MessagingNames.queueName);
//    }

}

