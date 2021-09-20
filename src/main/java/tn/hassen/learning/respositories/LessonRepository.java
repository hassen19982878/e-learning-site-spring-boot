package tn.hassen.learning.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.hassen.learning.models.Lesson;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {
}
