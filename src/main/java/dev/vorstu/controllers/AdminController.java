package dev.vorstu.controllers;

import dev.vorstu.entity.Student;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/admin")
@PreAuthorize("hasAuthority('Admin')")
public class AdminController {

    private final StudentRepository studentRepository;

    @Autowired
    public AdminController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("students")
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PostMapping("students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student newStudent) {
        return addStudent(newStudent);
    }

    private Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @DeleteMapping("students/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        removeStudent(id);
    }

    private void removeStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @PutMapping("students")
    public Student changeStudent(@RequestBody Student changingStudent) {
        return updateStudent(changingStudent);
    }

    private Student updateStudent(Student student) {
            return studentRepository.save(student);
        }
}