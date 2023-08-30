package dev.vorstu;

//import dev.vorstu.entity.User;
//import dev.vorstu.repositories.UserRepository;
//import dev.vorstu.entity.Password;
//import dev.vorstu.entity.Role;
import dev.vorstu.entity.Student;
import dev.vorstu.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Autowired
    private StudentRepository studentRepository;
    //@Autowired
//    private UserRepository userRepository;
    public void initial() {

        studentRepository.save(new Student("Эми Джонсон", "213", 3321));
        studentRepository.save(new Student("Глори Бекинс", "212", 711276433));
        studentRepository.save(new Student("Хасбик Бигс", "211", 7311139));
        studentRepository.save(new Student("Валерий Нечаев", "213", 899902931));
        studentRepository.save(new Student("Глеб Петренко", "212", 77632433));
        studentRepository.save(new Student("Олег Скобов", "211", 44442));
//        User studentUser= new User(null, "student", Role.STUDENT, new Password("1234"), true);
//        userRepository.save(studentUser);
//        Student johnWashington =new Student ("Джон Вашингтон", "213", 339);
//        johnWashington.setUser(studentUser);
//        studentRepository.save(johnWashington);
//        studentUser= new User(null, "admin", Role.ADMIN, new Password("sadmin"), true);
//        userRepository.save(studentUser);
//
    }
}
