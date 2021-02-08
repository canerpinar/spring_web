package org.example.config;

import org.example.utils.Deneme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public Deneme getDeneme(){
        System.out.println("Deneme sınıfı oluştu");
        return new Deneme();
    }



}
