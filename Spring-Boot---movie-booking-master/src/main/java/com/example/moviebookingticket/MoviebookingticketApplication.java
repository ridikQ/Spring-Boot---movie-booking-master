package com.example.moviebookingticket;

import org.flywaydb.core.Flyway;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;


@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class MoviebookingticketApplication {


    public static void main(String[] args) {
        SpringApplication.run(MoviebookingticketApplication.class, args);
    }


}


