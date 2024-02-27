package com.kjq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class MaintainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaintainApplication.class, args);
    }
}
