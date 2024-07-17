package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private int age;

    public Student(long id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Student{id=%d, firstName='%s', lastName='%s', age=%d}", id, firstName, lastName, age);
    }
}
