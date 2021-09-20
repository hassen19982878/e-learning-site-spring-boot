package tn.hassen.learning.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="enrolls")
public class Enrolled {

    @Id
    @GeneratedValue
    public Integer id;


    @ManyToOne()
    @JoinColumn(name="student_id")

   @JsonIgnoreProperties("enrolls")
    public Student student;

    @ManyToOne
    @JoinColumn(name="course_id")
    @JsonIgnoreProperties({"category","enrolls"})
    public Course course;

    @Column(name="enrolledAt",nullable = false)
    public LocalDate enrolledAt=LocalDate.now();
}
