package ru.bl00dphant0m.cinematelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "ru.bl00dphant0m.cinematelegrambot")
@EnableScheduling
@EnableFeignClients
public class CinemaTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaTelegramBotApplication.class, args);
    }

}
