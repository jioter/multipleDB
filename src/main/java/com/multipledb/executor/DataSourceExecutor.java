package com.multipledb.executor;

import com.multipledb.configs.datasource.DataSourceContextHolder;
import com.multipledb.configs.datasource.DataSourceProperties;
import com.multipledb.configs.datasource.DataSourceProperties.DataSourceParameters;
import com.multipledb.repository.UserRepository;
import java.util.List;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSourceExecutor {

    private final DataSourceProperties dataSources;
    private final DataSourceContextHolder dataSourceContextHolder;

    public <T> List<T> execute(Function<UserRepository, List<T>> repositoryOperation,
        UserRepository userRepository) {
        return dataSources.getDataSources().stream()
            .peek(this::setCurrentDatabaseName)
            .flatMap(dataSource -> repositoryOperation.apply(userRepository).stream())
            .toList();
    }

    private void setCurrentDatabaseName(DataSourceParameters dataSource) {
        dataSourceContextHolder.setCurrentDb(dataSource.name());
    }
}