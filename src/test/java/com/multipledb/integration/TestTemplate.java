package com.multipledb.integration;

import com.multipledb.MultipleDbApp;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.containers.PostgreSQLContainer;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(classes = MultipleDbApp.class)
public class TestTemplate {
    private static final String POSTGRES_VERSION = "postgres:latest";
    static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_VERSION));

    private static final String MYSQL_VERSION = "mysql:latest";
    static final MySQLContainer<?> MYSQL_CONTAINER = new MySQLContainer<>(DockerImageName.parse(MYSQL_VERSION));

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        POSTGRES_CONTAINER.withDatabaseName("db1");
        POSTGRES_CONTAINER.withNetworkAliases("data-base-1");
        POSTGRES_CONTAINER.withUsername("testuser");
        POSTGRES_CONTAINER.withPassword("testpassword");
        POSTGRES_CONTAINER.withInitScript("sql/postgres/create_table_and_users.sql");
        registry.add("POSTGRES_URL", POSTGRES_CONTAINER::getJdbcUrl);
        POSTGRES_CONTAINER.start();
    }

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        MYSQL_CONTAINER.withDatabaseName("db2");
        MYSQL_CONTAINER.withNetworkAliases("data-base-2");
        MYSQL_CONTAINER.withUsername("testuser");
        MYSQL_CONTAINER.withPassword("testpassword");
        MYSQL_CONTAINER.withInitScript("sql/mysql/create_table_and_users.sql");
        registry.add("MYSQL_URL", MYSQL_CONTAINER::getJdbcUrl);
        MYSQL_CONTAINER.start();
    }
}
