package tn.hassen.learning.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.hassen.learning.models.Enrolled;

public interface EnrollRepository extends JpaRepository<Enrolled,Integer> {
}
