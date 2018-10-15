package com.fsoft.stu.dapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@SuppressWarnings("PMD")
public class DemoApplication extends SpringBootServletInitializer {

    public static final String queueName = "stu";
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
