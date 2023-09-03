package dev.vorstu.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {
    public Student() {}
    public  Student(String fio, String group, Number phoneNumber){
        this.fio=fio;
        this.group=group;
        this.phoneNumber= phoneNumber;
    }
    public  Student(Long id,String fio, String group, Number phoneNumber){
        this(fio, group, phoneNumber);
        this.id=id;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String fio;
    @Column(name="group_of_students")
    private String group;
    private  Number phoneNumber;
    public String getFio(){
        return fio;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setFio(String fio){
        this.fio=fio;
    }
    public String getGroup(){
        return group;
    }
    public void setGroup(String group){
        this.group=group;
    }
    public  Number getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(Number phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    @JsonIgnore
    @OneToOne (mappedBy = "student")
    private User user;

    public User getUser() {

        if (user != null) {
            return user;
        }
        return null;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getUsername() {
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }

}
