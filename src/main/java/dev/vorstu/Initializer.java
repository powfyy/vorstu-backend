package dev.vorstu;

import dev.vorstu.entity.User;
import dev.vorstu.repositories.UserRepository;
import dev.vorstu.entity.Password;
import dev.vorstu.entity.Role;
import dev.vorstu.entity.Student;
import dev.vorstu.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Initializer {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    public void initial() {

        User studentUser= new User(null, "student", Role.STUDENT, new Password("1234"), true);
        User adminUser = new User (null, "admin", Role.ADMIN, new Password("sadmin"), true);
        Student student1 = new Student("Эми Джонсон", "213", 3321);
        Student studentAdmin = new Student("Admin", "666", 777777777);
        studentUser.setStudent(student1);
        adminUser.setStudent(studentAdmin);
        List <User> allUsers = Arrays.asList(studentUser, adminUser);
        userRepository.saveAll(allUsers);
        List <Student> allStudents = Arrays.asList(student1, studentAdmin);
        studentRepository.saveAll(allStudents);
        studentRepository.save(new Student("Глори Бекинс", "212", 711276433));
        studentRepository.save(new Student("Хасбик Бигс", "211", 7311139));
        studentRepository.save(new Student("Валерий Нечаев", "213", 899902931));
        studentRepository.save(new Student("Глеб Петренко", "212", 77632433));
        studentRepository.save(new Student("Олег Скобов", "211", 44442));
        studentRepository.save(new Student("Олег Скобов", "211", 44442));
    }
}
