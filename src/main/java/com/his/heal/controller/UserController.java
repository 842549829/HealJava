package com.his.heal.controller;

import com.his.heal.dto.UserDto;
import com.his.heal.provider.at.contract.AtProviderHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final AtProviderHolder atProviderHolder;

    @Autowired
    public UserController(AtProviderHolder atProviderHolder) {
        this.atProviderHolder = atProviderHolder;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {

        var atProvider = atProviderHolder.getAtProvider();
        if (atProvider == null) {
            return new UserDto(1, "张国荣");
        }else {
            return new UserDto(1, "张国荣" + atProvider.getAtName());
        }
    }

    @GetMapping("/index")
    @PreAuthorize("permitAll()")
    public  String index(){
        return "测试放行";
    }
}
