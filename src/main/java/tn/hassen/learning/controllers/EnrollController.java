package tn.hassen.learning.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.hassen.learning.models.Course;
import tn.hassen.learning.models.Enrolled;
import tn.hassen.learning.models.Instructor;
import tn.hassen.learning.respositories.CourseRepository;
import tn.hassen.learning.respositories.EnrollRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("enroll")
public class EnrollController {

    EnrollRepository enrollRepository;

    @Autowired
    public EnrollController(EnrollRepository enrollRepository)
    {this.enrollRepository = enrollRepository;
    }

    @PostMapping(path ="add")
    public ResponseEntity<Enrolled> addEnroll(@RequestBody Enrolled enroll) {
        Enrolled savedEnroll = this.enrollRepository.save(enroll);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEnroll);
    }
    @GetMapping(path = "all")
    public ResponseEntity<List<Enrolled>> getAllEnrolls() {
        List<Enrolled> enrolls = this.enrollRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(enrolls);
    }




}
