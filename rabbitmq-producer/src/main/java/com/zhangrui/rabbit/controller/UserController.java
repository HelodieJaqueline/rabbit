package com.zhangrui.rabbit.controller;

import com.zhangrui.rabbit.domain.User;
import com.zhangrui.rabbit.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZhangRui
 * @Date: Created at 2019-11-22-16:59
 * @Description:
 * @Modified: By
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public User register(User user) {
        log.info("register User:{}", user);
        userService.register(user);
        return user;
    }
}
