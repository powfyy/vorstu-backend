package dev.vorstu.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {
    private String username;
    private String password;
    private String fio;
    private String group;
    private  Number phoneNumber;
}
