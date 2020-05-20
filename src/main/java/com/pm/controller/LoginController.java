package com.pm.controller;


import com.pm.model.Student;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LoginController {

    @RequestMapping("/login")
    public Student login() {
        Student student = new Student();
        student.setName("Dileep");
        return student;
    }

}
