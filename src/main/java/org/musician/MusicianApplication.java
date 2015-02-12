package org.musician;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicianApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MusicianApplication.class);
        application.run(args);
    }
}