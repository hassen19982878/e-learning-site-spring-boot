package tn.hassen.learning.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(name="name",nullable = false)
    public String name;

    @Column(name="description",nullable = false)
    public String description;



    @Column(name = "imageUrl", nullable = false)
    public String imageUrl;


    @ManyToOne
    @JoinColumn(name = "idCategory")
    @JsonIgnoreProperties("courses")
    public Category category;

    @ManyToOne
    @JoinColumn(name = "idInstructor")
    @JsonIgnoreProperties("courses")
    public Instructor instructor;

    @OneToMany(mappedBy ="course")
    @JsonIgnoreProperties({"lesson","course"})
    public List<Lesson> lessons;

    @OneToMany(mappedBy = "course")
    @JsonIgnoreProperties("course")
    public List<Enrolled> enrolls;
}

