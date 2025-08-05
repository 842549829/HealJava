package com.his.heal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.his.heal.mapper")
@SpringBootApplication
@EnableCaching
public class HealApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealApplication.class, args);
    }
}
