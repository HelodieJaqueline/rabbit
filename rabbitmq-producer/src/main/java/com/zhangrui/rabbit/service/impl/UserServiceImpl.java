package com.zhangrui.rabbit.service.impl;

import com.zhangrui.rabbit.producer.config.RabbitConfig;
import com.zhangrui.rabbit.domain.User;
import com.zhangrui.rabbit.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ZhangRui
 * @Date: Created at 2019-11-22-17:09
 * @Description:
 * @Modified: By
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Autowired
    AmqpTemplate myRabbitTemplate;

    @Override
    public void register(User user) {
        myRabbitTemplate.convertAndSend(rabbitConfig.getUserTopicExchange(), rabbitConfig.getRoutingKey1(), user);
        log.info("produced register user message:{}", user);
    }
}
