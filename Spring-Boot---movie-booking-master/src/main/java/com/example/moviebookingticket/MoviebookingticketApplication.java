package com.example.moviebookingticket;


import com.example.moviebookingticket.auth.AuthenticationService;
import com.example.moviebookingticket.auth.RegisterRequest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.moviebookingticket.entity.RoleEntity.*;


@SpringBootApplication
public class MoviebookingticketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviebookingticketApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin= RegisterRequest.builder()
                    .firstName("Admin")
                    .lastName("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: "+service.register(admin).getAccessToken());

            var user=RegisterRequest.builder()
                    .firstName("USER")
                    .lastName("USER")
                    .email("user@mail.com")
                    .password("password")
                    .role(USER)
                    .build();
            System.out.println("USER token: "+service.register(user).getAccessToken());
        };
    }
}