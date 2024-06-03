package com.gaurav.controllers;

import java.util.ArrayList;
import java.util.List;

import com.gaurav.beans.Student;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    public static List<Student> students = new ArrayList<Student>();

    public StudentController(){
        students.add(new Student("King","Kochar"));
        students.add(new Student("Shreya","Sharma"));
        students.add(new Student("Sudip","Lama"));
        students.add(new Student("Ashim","Jha"));


    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/student/{firstName}/{lastName}")
    public Student studentPathVariable(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        return new Student(firstName, lastName);
    }

    //http://localhost:8080/student/query?firstName=Gaurav&lastName=Sharma
    @GetMapping("student/query")
    public Student studentQueryparams(@RequestParam(name="firstName") String firstName, @RequestParam(name="lastName") String lastName){
        return new Student(firstName,lastName);
    }

    @PostMapping("/student")
    public void addStudent(@RequestBody Student student){
        students.add(student);
    }

    @PutMapping("/student/{firstName}")
    public void updateStudent(@PathVariable("firstName") String firstName, @RequestBody Student student){
        for(Student stud: students){
            if (stud.getFirstName().equals(firstName)){
                stud.setFirstName(student.getFirstName());
                stud.setLastName(student.getLastName());
            }
        }
    }

}
