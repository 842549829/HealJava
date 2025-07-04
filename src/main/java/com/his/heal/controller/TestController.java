package com.his.heal.controller;

import com.his.heal.services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
@Slf4j // 日志注解 这样写就库直接使用 log.*
public class TestController {

    private static final Logger logger =  LoggerFactory.getLogger(TestController.class);

    private final UserServices userServices;

    public TestController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public  String index(){
        try {
            log.info("<UNK>");

            logger.error("错误测试");

            var user = userServices.findById("3a194f8d-d797-004e-d3b2-89c6a9684089");
            return user.getUserName();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
