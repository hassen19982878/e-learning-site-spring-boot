package tn.hassen.learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.hassen.learning.models.Course;
import tn.hassen.learning.respositories.CourseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("courses")
public class CourseController {
        CourseRepository courseRepository;

        @Autowired
        public CourseController(CourseRepository courseRepository)
        {
            this.courseRepository = courseRepository;
        }

        @GetMapping(path = "all")
        public ResponseEntity<List<Course>> getAllCourses() {
            List<Course> courses = this.courseRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        }

        @GetMapping(path = "one/{id}")
        public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
            try {
                Course course = courseRepository.findById(id).get();
                return ResponseEntity.status(HttpStatus.OK).body(course);

            }catch(Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Course());
            }
        }

        @PostMapping(path ="add")
        public ResponseEntity<Course> addCourse(@RequestBody Course course) {
            Course savedCourse = this.courseRepository.save(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
        }

        @PatchMapping(path="update")
        public ResponseEntity<Course> updateCourseById(@RequestBody Course course)
        {Course updateCourse=this.courseRepository.save(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(updateCourse);
        }

        @DeleteMapping(path="delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Map<String,String>> deleteCourse(@PathVariable Integer id)
        {
            this.courseRepository.deleteById(id);

            HashMap<String,String> objet=new HashMap<>();
            objet.put("message","course deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(objet);
        }
    }
