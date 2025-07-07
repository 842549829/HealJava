package com.his.heal.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * 自定义配置类
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "custom.jwt")
public class CustomJwtConfig {
    private String jwkSetUri;
    private ArrayList<String> requestMatchers;
}
