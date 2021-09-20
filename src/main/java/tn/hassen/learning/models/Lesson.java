package tn.hassen.learning.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="lesson")
public class Lesson {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(name="title",nullable = false)
    public String title;

    @Column(name="content",nullable = false)
    public String content;

    @ManyToOne
    @JsonIgnoreProperties({"lessons","category","enrolls","instructor"})
    @JoinColumn(name = "idCourse")
    public Course course;



}
