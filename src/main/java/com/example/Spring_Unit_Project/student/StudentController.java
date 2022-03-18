package com.example.Spring_Unit_Project.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }


    @DeleteMapping("/{studentId}")
    public void deleteStudent(
            @PathVariable Long studentId
    ) {

        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exists"
            );

        }
        studentRepository.deleteById(studentId);

    }
}
