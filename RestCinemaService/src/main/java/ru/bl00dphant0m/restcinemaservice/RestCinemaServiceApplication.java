package ru.bl00dphant0m.restcinemaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestCinemaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestCinemaServiceApplication.class, args);
    }

}
