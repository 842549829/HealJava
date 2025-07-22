package com.his.heal.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
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

/*    @DurationUnit(ChronoUnit.SECONDS)
    private Duration serverTime;

    @DataSizeUnit(DataUnit.BYTES)
    private DataSize dataSize ;*/
}
