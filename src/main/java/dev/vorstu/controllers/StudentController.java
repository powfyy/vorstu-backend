package dev.vorstu.controllers;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entity.Student;
import dev.vorstu.entity.User;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.UserRepository;
import dev.vorstu.services.StudentService;
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
    @Autowired
    private StudentService studentService;
    @GetMapping ("/students")
    public StudentDTO[] getStudent(Principal user) {
        User currentUser = (userRepository.findByUsername(user.getName())).get();
        StudentDTO[] studentDTOs ={studentService.findById((currentUser.getStudent()).getId())};
        return studentDTOs;
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

}
