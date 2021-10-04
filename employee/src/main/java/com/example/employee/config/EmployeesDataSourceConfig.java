package com.example.employee.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories("com.example.employee.repository")
@Getter
public class EmployeesDataSourceConfig {
    @Value("${spring.datasource.url:''}")
    private String dbUrl;
    @Value("${spring.datasource.username:''}")
    private String dbUsername;
    @Value("${spring.datasource.password:''}")
    private String dbPassword;
    @Value("${hibernate.dialect:''}")
    private String dialect;

    @Primary
    @Bean(name = "h2DataSource")
    public DataSource createHSQLDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(dbUrl);
        dataSourceBuilder.username(dbUsername);
        dataSourceBuilder.password(dbPassword);
        return dataSourceBuilder.build();
    }

}
