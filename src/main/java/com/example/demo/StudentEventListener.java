package com.example.demo;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @EventListener
    public void handleStudentCreated(StudentCreatedEvent event) {
        System.out.println("Created student: " + event.getStudent());
    }

    @EventListener
    public void handleStudentDeleted(StudentDeletedEvent event) {
        System.out.println("Deleted student with ID: " + event.getStudentId());
    }
}

