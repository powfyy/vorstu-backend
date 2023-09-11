package dev.vorstu.services;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class MappingService {
    public static StudentDTO mapToStudentDTO(Student entity){
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setFio(entity.getFio());
        dto.setGroup(entity.getGroup());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }
    public Student mapToStudent(StudentDTO dto){
        Student entity = new Student();
        entity.setId(dto.getId());
        entity.setFio(dto.getFio());
        entity.setGroup(dto.getGroup());
        entity.setPhoneNumber(dto.getPhoneNumber());
        return entity;
    }
}
