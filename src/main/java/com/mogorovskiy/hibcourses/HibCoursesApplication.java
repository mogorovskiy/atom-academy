package com.mogorovskiy.hibcourses;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibCoursesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HibCoursesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World");
    }
}
