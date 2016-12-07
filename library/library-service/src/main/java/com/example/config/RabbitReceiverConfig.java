package com.example.config;

import com.example.service.Receiver;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class RabbitReceiverConfig {

    private static Logger logger = LoggerFactory.getLogger(RabbitReceiverConfig.class);

//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Bean
//    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        return rabbitTemplate;
//    }

    @Bean
    Queue queue(){
        return new Queue(MessagingNames.queueName, false);
    }


    @Bean
    TopicExchange exchange(){
        return new TopicExchange(MessagingNames.exchangeName);
    }

//    @Bean
//    Binding binding(Queue queue, TopicExchange topicExchange){
//        Binding binding;
//        binding = BindingBuilder.bind(queue).to(topicExchange).with(MessagingNames.routingKeyCreate);
//        binding = BindingBuilder.bind(queue).to(topicExchange).with(MessagingNames.routingKeyDelete);
//        binding = BindingBuilder.bind(queue).to(topicExchange).with(MessagingNames.routingKeyUpdate);
//        return binding;
//    }
      @Bean
      List<Binding> bindings(Queue queue, TopicExchange topicExchange){
        return Arrays.asList(BindingBuilder.bind(queue).to(topicExchange).with(MessagingNames.routingKeyCreate),
                BindingBuilder.bind(queue).to(topicExchange).with(MessagingNames.routingKeyDelete),
                BindingBuilder.bind(queue).to(topicExchange).with(MessagingNames.routingKeyUpdate));
    }


    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             Receiver receiver){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(MessagingNames.queueName);
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                System.out.println("!!!!!!!!!"+message.getMessageProperties().getReceivedRoutingKey());
                switch (message.getMessageProperties().getReceivedRoutingKey()){
                    case MessagingNames.routingKeyCreate: {
                        receiver.create(message, channel);
                        break;
                    }
                    case MessagingNames.routingKeyUpdate: {
                        receiver.update(message, channel);
                        break;
                    }
                    case MessagingNames.routingKeyDelete: {
                        receiver.delete(message, channel);
                        break;
                    }
                    default: {
                        receiver.receiveMessage(message, channel);
                        break;
                    }
                }

            }
        });


        return container;

    }

    @Bean
    Receiver receiver(){
        return new Receiver();
    }




}
