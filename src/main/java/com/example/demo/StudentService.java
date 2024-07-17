package com.example.demo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();
    private final ApplicationEventPublisher eventPublisher;

    public StudentService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public Student addStudent(String firstName, String lastName, int age) {
        long id = idGenerator.incrementAndGet();
        Student student = new Student(id, firstName, lastName, age);
        students.put(id, student);
        eventPublisher.publishEvent(new StudentCreatedEvent(this, student));
        return student;
    }

    public Student getStudentById(long id) {
        return students.get(id);
    }

    public List<Student> findStudentsByFirstName(String firstName) {
        return students.values().stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
    }

    public Student updateStudent(long id, String firstName, String lastName, int age) {
        Student studentToUpdate = students.get(id);
        if (studentToUpdate != null) {
            studentToUpdate.setFirstName(firstName);
            studentToUpdate.setLastName(lastName);
            studentToUpdate.setAge(age);
            return studentToUpdate;
        }
        return null;
    }

    public Student removeStudent(long id) {
        Student removedStudent = students.remove(id);
        if (removedStudent != null) {
            eventPublisher.publishEvent(new StudentDeletedEvent(this, id));
        }
        return removedStudent;
    }

    public void clearAllStudents() {
        students.clear();
    }
}
