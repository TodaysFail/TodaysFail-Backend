package com.todaysfail.config.security;

import com.todaysfail.common.helper.SpringEnvironmentHelper;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class CorsConfig implements WebMvcConfigurer {
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        ArrayList<String> allowedOriginPatterns = new ArrayList<>();
        allowedOriginPatterns.add("https://todaysfail.com");
        allowedOriginPatterns.add("https://dev.todaysfail.com");
        if (!springEnvironmentHelper.isProdProfile()) {
            allowedOriginPatterns.add("http://localhost:3000");
            allowedOriginPatterns.add("http://localhost:5173");
        }
        String[] patterns = allowedOriginPatterns.toArray(String[]::new);
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOriginPatterns(patterns)
                .exposedHeaders("Set-Cookie")
                .allowCredentials(true);
    }
}
