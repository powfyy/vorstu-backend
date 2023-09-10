package dev.vorstu.controllers;

import dev.vorstu.entity.Role;
import dev.vorstu.entity.Student;
import dev.vorstu.entity.User;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;


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
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            User user = student.getUser();
            if (user != null && user.getRole()!= Role.ADMIN) {
                userRepository.delete(user);
            }
            studentRepository.delete(student);
        }
    }

    @PutMapping("students")
    public Student changeStudent(@RequestBody Student changingStudent) {
        return updateStudent(changingStudent);
    }

    private Student updateStudent(Student student) {
            return studentRepository.save(student);
        }
}