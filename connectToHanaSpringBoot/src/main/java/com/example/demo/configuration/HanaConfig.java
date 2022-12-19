package com.example.demo.configuration;

import com.sap.db.jdbc.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class HanaConfig {

    //pega os valores das variáveis de ambiente do sistema

    @Value("${DBURL}")
    private String url;

    @Value("${DBUSERNAME}")
    private String username;

    @Value("${DBPASSWORD}")
    private String password;

    //configura o user, senha e url do HANA
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
