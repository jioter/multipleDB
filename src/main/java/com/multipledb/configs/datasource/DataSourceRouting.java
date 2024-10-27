package com.multipledb.configs.datasource;

import static java.util.stream.Collectors.toMap;

import com.multipledb.configs.datasource.DataSourceProperties.DataSourceParameters;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import java.util.Map;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceRouting extends AbstractRoutingDataSource {

    private final DataSourceProperties properties;
    private final DataSourceContextHolder dataSourceContextHolder;

    @PostConstruct
    private void initDataSourceContextHolder() {
        Map<Object, Object> dataSourceMap = properties.getDataSources().stream()
            .collect(toMap(DataSourceParameters::name, this::mapToDataSource));
        this.setTargetDataSources(dataSourceMap);
        var defaultDatabaseName = properties.getDataSources().stream().findFirst().get().name();
        this.setDefaultTargetDataSource(dataSourceMap.get(defaultDatabaseName));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceContextHolder.getCurrentDb();
    }

    public DataSource mapToDataSource(DataSourceParameters properties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(properties.strategy().getGetDriverClassName());
        hikariConfig.setJdbcUrl(properties.url());
        hikariConfig.setUsername(properties.user());
        hikariConfig.setPassword(properties.password());
        return new HikariDataSource(hikariConfig);
    }
}
