package com.multipledb.configs.datasource;

import static org.springframework.boot.jdbc.DatabaseDriver.POSTGRESQL;
import static org.springframework.boot.jdbc.DatabaseDriver.MYSQL;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "data-sources")
@RequiredArgsConstructor
public class DataSourceProperties {

    private final List<DataSourceParameters> dataSources;

    public record DataSourceParameters(String name, Strategy strategy, String url, String table, String user, String password, Mapping mapping) { }

    public record Mapping(String id, String username, String name, String surname) { }

    @Getter
    public enum Strategy {
        postgres(POSTGRESQL.getDriverClassName()),
        mysql(MYSQL.getDriverClassName());

        private final String getDriverClassName;

        private Strategy(String driverClassName) {
            this.getDriverClassName = driverClassName;
        }
    }
}
