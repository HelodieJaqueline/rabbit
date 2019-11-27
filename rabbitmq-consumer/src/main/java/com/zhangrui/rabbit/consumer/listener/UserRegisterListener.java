package com.zhangrui.rabbit.consumer.listener;

import com.zhangrui.rabbit.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhangRui
 * @Date: Created at 2019-11-22-17:35
 * @Description:
 * @Modified: By
 */
//@Component
//@PropertySource("classpath:rabbit.properties")
//@RabbitListener(queues = "${user.user-queue}", containerFactory="rabbitListenerContainerFactory")
@Component
@PropertySource("classpath:application.yml")
@RabbitListener(queues = "${spring.rabbitmq.user.user-queue}", containerFactory="rabbitListenerContainerFactory")
@Slf4j
public class UserRegisterListener {

    @RabbitHandler
    public void process(@Payload User msg) {
        log.info("consumer register User message:{}", msg);
    }
}
