package tn.hassen.learning.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.hassen.learning.models.Course;

public interface CourseRepository extends JpaRepository<Course,Integer> {



}
