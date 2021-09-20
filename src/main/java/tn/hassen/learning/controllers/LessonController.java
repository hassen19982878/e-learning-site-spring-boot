package tn.hassen.learning.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.hassen.learning.models.Course;
import tn.hassen.learning.models.Lesson;
import tn.hassen.learning.respositories.LessonRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("lessons")

public class LessonController {
    LessonRepository lessonRepository;

    @Autowired
    public LessonController(LessonRepository lessonRepository)
    {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = this.lessonRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(lessons);
    }


    @GetMapping(path = "one/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Integer id) {
        try {
            Lesson lesson = lessonRepository.findById(id).get();
            return ResponseEntity.status(HttpStatus.OK).body(lesson);

        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Lesson());
        }
    }
    @PostMapping(path ="add")
    public ResponseEntity<Lesson> addLesson(@RequestBody Lesson lesson) {
        Lesson savedLesson = this.lessonRepository.save(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
    }
    @PatchMapping(path="update")
    public ResponseEntity<Lesson> updateLessonById(@RequestBody Lesson lesson)
    {Lesson updateLesson=this.lessonRepository.save(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateLesson);
    }
    @DeleteMapping(path="delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> deleteLesson(@PathVariable Integer id)
    {
        this.lessonRepository.deleteById(id);

        HashMap<String,String> objet=new HashMap<>();
        objet.put("message","lesson deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(objet);
    }

}
