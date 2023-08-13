package com.todaysfail.config.swagger;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.helper.SpringEnvironmentHelper;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private static final String SERVER_NAME = "TodaysFail";
    private static final String API_TITLE = "TodaysFail API 문서";
    private static final String API_DESCRIPTION = "TodaysFail API 문서입니다.";
    private static final String GITHUB_URL = "https://github.com/TodaysFail/TodaysFail-Backend";

    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Value("${springdoc.swagger-ui.version}")
    private String swaggerVersion;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(swaggerServers())
                .info(swaggerInfo())
                .components(authSetting());
    }

    private List<Server> swaggerServers() {
        Server server = new Server();
        String serverUrl = getServerUrl();
        server.url(serverUrl);
        server.description(API_DESCRIPTION);
        return List.of(server);
    }

    private String getServerUrl() {
        switch (springEnvironmentHelper.getCurrentProfile()) {
            case "prod":
                return PROD_SERVER_URL;
            case "dev":
                return DEV_SERVER_URL;
            default:
                return LOCAL_SERVER_URL;
        }
    }

    private Info swaggerInfo() {
        License license = new License();
        license.url(GITHUB_URL);
        license.setName(SERVER_NAME);
        return new Info()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(swaggerVersion)
                .license(license);
    }

    private Components authSetting() {
        return new Components()
                .addSecuritySchemes(
                        "access-token",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization"));
    }
}
