package tn.hassen.learning.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.hassen.learning.models.Instructor;
import tn.hassen.learning.models.Student;
import tn.hassen.learning.respositories.StudentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("students")
public class StudentController {
    private StudentRepository studentRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @GetMapping(path = "all")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = this.studentRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }
    @PutMapping(path="update")
    public ResponseEntity<Student> updateStudentById(@RequestBody Student student)
    {
        Student updateStudent=this.studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateStudent);
    }

    @DeleteMapping(path="delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> deleteStudent(@PathVariable Integer id)
    {this.studentRepository.deleteById(id);
        HashMap<String,String> objet=new HashMap<>();
        objet.put("message","student deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(objet);
    }

    @RequestMapping(path="register")
    public ResponseEntity<Student> addStudent(@RequestBody Student student)
    {   student.password=this.bCryptPasswordEncoder.encode(student.password);
        Student savedStudent=studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginStudent(@RequestBody Student student) {

        HashMap<String, Object> response = new HashMap<>();

        Student studentFromDB = studentRepository.findByEmail(student.email);//

        if (studentFromDB == null) {
            response.put("message", "student not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            Boolean compare = this.bCryptPasswordEncoder.matches(student.password, studentFromDB.password);

            if (!compare) {
                response.put("message", "student not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {

                if (!studentFromDB.accountState) {
                    response.put("message", "student not allowed !");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                } else {
                    String token = Jwts.builder()
                            .claim("id",studentFromDB.id)
                            .claim("role",studentFromDB.role)
                            //.claim("data", studentFromDB)
                            .signWith(SignatureAlgorithm.HS256, "SECRET")
                            .compact();

                    response.put("token", token);
                    System.out.println("hhh");
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }

}
