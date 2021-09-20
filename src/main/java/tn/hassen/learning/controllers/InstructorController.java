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
import tn.hassen.learning.respositories.InstructorRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("instructors")
public class InstructorController {

    private InstructorRepository instructorRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @PostMapping(path = "register")
    public ResponseEntity<Instructor> addInstructor(@RequestBody Instructor instructor) {
        instructor.password = this.bCryptPasswordEncoder.encode(instructor.password);
        Instructor savedUser = instructorRepository.save(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = this.instructorRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(instructors);
    }
    @PutMapping(path="update")
    public ResponseEntity<Instructor> updateInstructorById(@RequestBody Instructor instructor)
    {
        Instructor updateInstructor=this.instructorRepository.save(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateInstructor);
    }
    @DeleteMapping(path="delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> deleteInstructor(@PathVariable Integer id)
    {this.instructorRepository.deleteById(id);
        HashMap<String,String> objet=new HashMap<>();
        objet.put("message","instructor deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(objet);
    }
    @PostMapping(path = "login")
    public ResponseEntity<Map<String, Object>> loginInstructor(@RequestBody Instructor instructor) {

        HashMap<String, Object> response = new HashMap<>();

        Instructor instructorFromDB = instructorRepository.findByEmail(instructor.email);

        if (instructorFromDB == null) {
            response.put("message", "instructor not found !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {

            Boolean compare = this.bCryptPasswordEncoder.matches(instructor.password, instructorFromDB.password);

            if (!compare) {
                response.put("message", "user not found !");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {

                if (!instructorFromDB.accountState) {
                    response.put("message", "instructor not allowed !");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                } else {
                    String token = Jwts.builder()
                            .claim("id", instructorFromDB.id)
                            .claim("role",instructorFromDB.role)
                            .signWith(SignatureAlgorithm.HS256, "SECRET")
                            .compact();

                    response.put("token", token);

                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }
            }
        }
    }





}
