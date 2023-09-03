package dev.vorstu.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.*;

@Entity
@Table(name = "passwords")
@Getter @Setter @NoArgsConstructor
public class Password {
    static BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    public Password(String password){this.password = passwordEncoder.encode(password);}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    @JsonIgnore
    private void setPasswordWithEncoding(String password){this.password= passwordEncoder.encode(password);}
}
