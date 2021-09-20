package tn.hassen.learning.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(name = "firstname", nullable = false)
    public String firstname;

    @Column(name = "lastname", nullable = false)
    public String lastname;

    @Column(name = "email", unique = true, nullable = false)
    public String email;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "role")
    public String role = "student";

    @Column(name = "accountState")
    public Boolean accountState = true;


    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties({"enrolls","student","course"})
    public List<Enrolled> enrolls;

}
