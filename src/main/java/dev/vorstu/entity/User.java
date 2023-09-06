package dev.vorstu.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name= "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String username;

    @Enumerated (EnumType.STRING)
    private Role role;
    private String password;
    private boolean enable;
    public User(Long id, String username, Role role, String password, boolean enable){
        this.id=id;
        this.username=username;
        this.role=role;
        this.password=password;
        this.enable=enable;
    }
    public User(Long id, String username, Role role, String password){
        this.id=id;
        this.username=username;
        this.role=role;
        this.password=password;
    }
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public Role getRole() { return role; }




    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
}
