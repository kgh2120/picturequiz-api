package com.kk.picturequizapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PictureQuizApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PictureQuizApiApplication.class, args);
    }

}
