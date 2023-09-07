package dev.vorstu.controllers;

import dev.vorstu.entity.Student;
import dev.vorstu.entity.User;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping ("/students")
    public Student getStudent(Principal user) {
        User currentUser = (userRepository.findByUsername(user.getName())).get();
        return currentUser.getStudent();
    }

    @PutMapping (value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(Principal user, @RequestBody Student updatedStudent) {
        User currentUser = (userRepository.findByUsername(user.getName())).get();
        Student student = currentUser.getStudent();
        if(student != null) {
            student.setFio(updatedStudent.getFio());
            student.setGroup(updatedStudent.getGroup());
            student.setPhoneNumber(updatedStudent.getPhoneNumber());
            return studentRepository.save(student);
        }
        return null;
    }

    @DeleteMapping (value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(Principal user){
        User currentUser = (userRepository.findByUsername(user.getName())).get();
        Student student = currentUser.getStudent();
        if(student != null) {
            studentRepository.deleteById(student.getId());
            return student.getId();
        }
        return null;
    }
}
