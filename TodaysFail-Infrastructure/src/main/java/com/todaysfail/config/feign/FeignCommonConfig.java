package com.todaysfail.config.feign;

import static feign.Logger.*;

import com.todaysfail.outer.api.BaseFeignClientPackage;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(FeignClientErrorDecoder.class)
@EnableFeignClients(basePackageClasses = BaseFeignClientPackage.class)
public class FeignCommonConfig {

    @Bean
    Level feignLoggerLevel() {
        return Level.FULL;
    }
}
