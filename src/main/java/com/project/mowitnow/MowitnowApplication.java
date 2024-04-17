package com.project.mowitnow;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan(basePackages = "com.project.mowitnow")
public class MowitnowApplication {

    public static void main(String[] args) {
        SpringApplication.run(MowitnowApplication.class, args);
    }
}
