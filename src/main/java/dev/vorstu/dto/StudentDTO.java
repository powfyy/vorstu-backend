package dev.vorstu.dto;

import dev.vorstu.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class StudentDTO {
    private Long id;
    private String fio;
    private String group;
    private Number phoneNumber;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.fio = student.getFio();
        this.group = student.getGroup();
        this.phoneNumber = student.getPhoneNumber();

    }
}


