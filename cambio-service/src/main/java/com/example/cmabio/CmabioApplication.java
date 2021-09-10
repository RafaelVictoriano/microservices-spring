package com.example.cmabio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CmabioApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmabioApplication.class, args);
    }

}
