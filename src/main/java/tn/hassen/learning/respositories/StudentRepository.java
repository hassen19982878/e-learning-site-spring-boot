package tn.hassen.learning.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.hassen.learning.models.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByEmail(String email);

}
