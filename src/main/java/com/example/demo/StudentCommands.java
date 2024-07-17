package com.example.demo;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class StudentCommands {

    private final StudentService studentService;

    public StudentCommands(StudentService studentService) {
        this.studentService = studentService;
    }

    @ShellMethod("Show all students")
    public List<Student> showStudents() {
        return studentService.getAllStudents();
    }

    @ShellMethod("Add a new student")
    public String addStudent(@ShellOption String firstName, @ShellOption String lastName, @ShellOption int age) {
        Student student = studentService.addStudent(firstName, lastName, age);
        return "Added: " + student;
    }

    @ShellMethod("Remove a student by ID")
    public String removeStudent(@ShellOption long id) {
        Student student = studentService.removeStudent(id);
        return student != null ? "Removed student with ID: " + id : "Student not found";
    }

    @ShellMethod("Clear all students")
    public String clearStudents() {
        studentService.clearAllStudents();
        return "All students have been removed";
    }

    @ShellMethod("Show information about a student by ID")
    public String showStudent(@ShellOption long id) {
        Student student = studentService.getStudentById(id);
        return student != null ? student.toString() : "Student not found";
    }

    @ShellMethod("Update student information")
    public String updateStudent(@ShellOption long id, @ShellOption String firstName, @ShellOption String lastName, @ShellOption int age) {
        Student updatedStudent = studentService.updateStudent(id, firstName, lastName, age);
        return updatedStudent != null ? "Updated student: " + updatedStudent : "Student not found";
    }

    @ShellMethod("Find a student by first name")
    public String findStudentByName(@ShellOption String firstName) {
        List<Student> foundStudents = studentService.findStudentsByFirstName(firstName);
        if (foundStudents.isEmpty()) {
            return "No students found with first name: " + firstName;
        } else {
            StringBuilder result = new StringBuilder("Found students:\n");
            for (Student student : foundStudents) {
                result.append(student.toString()).append("\n");
            }
            return result.toString();
        }
    }

    @ShellMethod("Show total number of students")
    public String showStudentCount() {
        int count = studentService.getAllStudents().size();
        return "Total number of students: " + count;
    }
}
