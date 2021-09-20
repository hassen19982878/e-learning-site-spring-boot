package tn.hassen.learning.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="instructors")
public class Instructor {

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

    @Column(name= "imageUrl" , nullable = false)
    public String imageUrl;

    @Column(name="description")
    public String description;

    @Column(name = "role")
    public String role = "instructor";

    @Column(name = "accountState")
    public Boolean accountState = true;

    @OneToMany(mappedBy = "instructor")
    @JsonIgnoreProperties({"instructor","category","lessons","enrolls"})
    public List<Course> courses;

}

