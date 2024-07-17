package com.example.demo;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StudentDeletedEvent extends ApplicationEvent {
    private final long studentId;

    public StudentDeletedEvent(Object source, long studentId) {
        super(source);
        this.studentId = studentId;
    }

}