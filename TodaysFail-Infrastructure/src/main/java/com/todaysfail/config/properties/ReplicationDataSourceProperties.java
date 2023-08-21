package com.todaysfail.config.properties;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.context.annotation.Profile;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource")
@ConstructorBinding
@Profile({"prod", "dev"})
public class ReplicationDataSourceProperties {
    private String username;
    private String password;
    private String driverClassName;
    private Master master;
    private List<Slave> slave;
    private Hikari hikari;

    @Getter
    @AllArgsConstructor
    public static class Master {
        private String name;
        private String url;
    }

    @Getter
    @AllArgsConstructor
    public static class Slave {
        private String name;
        private String url;
    }

    @Getter
    @AllArgsConstructor
    public static class Hikari {
        private int maxLifetime;
        private int maximumPoolSize;
    }
}
