package dev.vorstu.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;
    private String fio;
    private String group;
    private Number phoneNumber;
}
