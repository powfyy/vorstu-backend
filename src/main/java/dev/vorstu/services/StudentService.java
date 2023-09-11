package dev.vorstu.services;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entity.Student;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.services.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> findAll() {
        return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .map(MappingService::mapToStudentDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO findById(Long id) {
        return MappingService.mapToStudentDTO(
                studentRepository.findById(id)
                        .orElse(new Student())
        );
    }
}
