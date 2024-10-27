package com.multipledb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@ConfigurationPropertiesScan("com.multipledb.configs")
@SpringBootApplication(scanBasePackages = {"com.multipledb"},
    exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MultipleDbApp {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDbApp.class, args);
    }
}