package com.zhangrui.rabbit.consumer.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ZhangRui
 * @Date: Created at 2019-11-22-15:21
 * @Description:
 * @Modified: By
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq.user")
public class RabbitConfig {

    private String userTopicExchange;

    private String userQueue;

    @Bean("userQueue")
    public Queue getFirstQueue(){
        return new Queue("USER_QUEUE");
    }

    @Bean("userTopicExchange")
    public TopicExchange getTopicExchange(){
        return new TopicExchange("USER_TOPIC_EXCHANGE");
    }

    @Bean
    public Binding bindFirst(@Qualifier("userQueue") Queue queue, @Qualifier("userTopicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.user.*");
    }

    /**
     * 在消费端转换JSON消息
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setAutoStartup(true);
        return factory;
    }
}
