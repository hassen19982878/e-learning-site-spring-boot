package tn.hassen.learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.hassen.learning.models.Category;
import tn.hassen.learning.respositories.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


    @CrossOrigin
    @RestController
    @RequestMapping("categories")
    public class CategoryController {

        CategoryRepository categoryRepository;

        @Autowired
        public CategoryController(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        @GetMapping(path = "all")
        public ResponseEntity<List<Category>> getAllCategories() {
            List<Category> categories = this.categoryRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(categories);
        }
        @PostMapping(path = "add")
        public ResponseEntity<Category> addCategory(@RequestBody Category category) {
            Category savedCategory = this.categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
        }

        @GetMapping(path="one/{id}")
        public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
            try {
                Category category = categoryRepository.findById(id).get();
                return ResponseEntity.status(HttpStatus.OK).body(category);
            }catch(Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Category());
            }
        }
        @PatchMapping(path="update")
        public ResponseEntity<Category> updateCategoryById(@RequestBody Category category)
        {Category updateCategory=this.categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(updateCategory);
        }

        @DeleteMapping(path="delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Map<String,String>> deleteCategory(@PathVariable Integer id)
        {
            this.categoryRepository.deleteById(id);

            HashMap<String,String> objet=new HashMap<>();
            objet.put("message","category deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(objet);

        }







    }
