package com.multipledb.configs.datasource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DataSourceContextHolder {

    private static ThreadLocal<String> contextHolder;

    public DataSourceContextHolder() {
        contextHolder = new ThreadLocal<>();
    }

    public void setCurrentDb(String dataSource) {
        contextHolder.set(dataSource);
    }

    public String getCurrentDb() {
        return contextHolder.get();
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}
