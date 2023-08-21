package com.todaysfail.config.mysql;

import com.todaysfail.config.properties.ReplicationDataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

@Configuration
@RequiredArgsConstructor
@Profile({"prod", "dev"})
public class ReplicationDataSourceConfiguration {
    private final ReplicationDataSourceProperties replicationDataSourceProperties;

    @Bean
    public DataSource routingDataSource() {
        ReplicationRoutingDataSource replicationRoutingDataSource =
                new ReplicationRoutingDataSource();

        ReplicationDataSourceProperties.Master master = replicationDataSourceProperties.getMaster();
        DataSource masterDataSource = createDataSource(master.getUrl());

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(master.getName(), masterDataSource);

        List<ReplicationDataSourceProperties.Slave> slaves =
                replicationDataSourceProperties.getSlave();
        for (ReplicationDataSourceProperties.Slave slave : slaves) {
            dataSourceMap.put(slave.getName(), createDataSource(slave.getUrl()));
        }

        replicationRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        replicationRoutingDataSource.setTargetDataSources(dataSourceMap);
        replicationRoutingDataSource.afterPropertiesSet();

        return new LazyConnectionDataSourceProxy(replicationRoutingDataSource);
    }

    private DataSource createDataSource(String url) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(replicationDataSourceProperties.getDriverClassName());
        hikariDataSource.setUsername(replicationDataSourceProperties.getUsername());
        hikariDataSource.setPassword(replicationDataSourceProperties.getPassword());
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setMaxLifetime(
                replicationDataSourceProperties.getHikari().getMaxLifetime());
        hikariDataSource.setMaximumPoolSize(
                replicationDataSourceProperties.getHikari().getMaximumPoolSize());
        return hikariDataSource;
    }
}
