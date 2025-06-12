package com.his.heal.controller;

import com.his.heal.services.UserServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    private final UserServices userServices;

    public TestController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public  String index(){
        try {
            var user = userServices.findById("3a194f8d-d797-004e-d3b2-89c6a9684089");
            return user.getUserName();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
