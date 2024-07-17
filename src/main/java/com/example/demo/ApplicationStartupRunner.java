package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements CommandLineRunner {

    private final StudentService studentService;

    public ApplicationStartupRunner(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) {
        if (Boolean.parseBoolean(System.getProperty("app.createStudentsOnStartup", "false"))) {
            studentService.addStudent("John", "Doe", 20);
            studentService.addStudent("Jane", "Smith", 22);
        }
    }
}
