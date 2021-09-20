package tn.hassen.learning.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.hassen.learning.models.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
