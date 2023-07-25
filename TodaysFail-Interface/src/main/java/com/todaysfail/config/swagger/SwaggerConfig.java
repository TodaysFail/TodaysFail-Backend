package com.todaysfail.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info =
                new Info()
                        .title("오늘의 실패 API 문서")
                        .description("오늘의 실패 API 문서입니다.")
                        .version("v0.0.1");
        return new OpenAPI().info(info);
    }

    private Info swaggerInfo() {
        License license = new License();
        license.url("https://github.com/TodaysFail/TodaysFail-Backend");
        license.setName("TodaysFail");
        return new Info()
                .title("TodaysFail API 문서")
                .description("TodaysFail API 문서입니다.")
                .version("v0.0.1")
                .license(license);
    }
}
