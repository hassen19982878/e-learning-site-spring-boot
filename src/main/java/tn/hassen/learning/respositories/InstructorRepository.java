package tn.hassen.learning.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.hassen.learning.models.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {
    Instructor findByEmail(String email);

}
