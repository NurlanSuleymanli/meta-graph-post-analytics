package com.nurlansuleymanli.metagraphapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MetaGraphApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetaGraphApiApplication.class, args);
    }

}
