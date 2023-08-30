package dev.vorstu.controllers;

//import dev.vorstu.entity.Role;
// import org.springframework.security.access.prepost.PreAuthorize;
////import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
////import org.springframework.security.core.Authentication;
////import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

import dev.vorstu.entity.Student;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@RequestMapping("api/base")
public class BaseContoller {
    @Autowired
    StudentRepository studentRepository;
    private Long counter=0L;
    private Long generateId(){return counter++;}
    @PostMapping(value ="students", produces= MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student newStudent ) {return addStudent(newStudent);}
    private Student addStudent(Student student){
        studentRepository.save(student);
        return student;
    }
    @GetMapping ("students")
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @GetMapping(value = "students/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudentById(@RequestParam(value="id") Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    @PutMapping (value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student changeStudent(@RequestBody Student changingStudent){
        return updateStudent (changingStudent);
    }
    private Student updateStudent(Student student){
        if(student.getId()==null){
            throw new RuntimeException("id of changing student cannot be null");
        }
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());
        Student changingStudent = optionalStudent.orElseThrow(() -> new RuntimeException("student with id: " + student.getId() + " was not found"));
        changingStudent.setFio(student.getFio());
        changingStudent.setGroup(student.getGroup());
        changingStudent.setPhoneNumber(student.getPhoneNumber());
        studentRepository.save(changingStudent);
        return changingStudent;
    }
    @DeleteMapping (value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(@PathVariable("id")Long id){
        return removeStudent(id);
    }
    private Long removeStudent(Long id ){
        studentRepository.deleteById(id);
        return id;
    }
}

//    public boolean hasRole(Principal principal, String roleName) {
//        if (principal instanceof Authentication) {
//            Authentication authentication = (Authentication) principal;
//            return authentication.getAuthorities().stream()
//                    .anyMatch(authority -> authority.getAuthority().equals(roleName));
//        }
//        return false;
//    }