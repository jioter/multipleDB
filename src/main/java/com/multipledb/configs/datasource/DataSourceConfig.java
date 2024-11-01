package com.multipledb.configs.datasource;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.multipledb.repository", entityManagerFactoryRef = "entityManager")
@EnableTransactionManagement
@RequiredArgsConstructor
@DependsOn("dataSourceRouting")
public class DataSourceConfig {

    final String PACKAGE_TO_SCAN_ENTITY = "com.multipledb.entity";

    private final DataSourceRouting dataSourceRouting;

    @Bean(name = "entityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSourceRouting);
        em.setPackagesToScan(PACKAGE_TO_SCAN_ENTITY);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName("usersPersistenceUnit");
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(
            Objects.requireNonNull(entityManagerFactoryBean.getObject()));
    }
}
