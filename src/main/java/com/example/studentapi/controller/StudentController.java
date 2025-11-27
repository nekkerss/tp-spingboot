package com.example.studentapi.controller;

import com.example.studentapi.entity.Student;
import com.example.studentapi.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:4200") // allow Angular frontend
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @GetMapping
    public List<Student> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Student s) {
        if (s.getAge() < 20) {
            return ResponseEntity.badRequest()
                    .body("Student must be at least 20 years old");
        }
        Student saved = repo.save(s);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Student s) {
        if (s.getAge() < 20) {
            return ResponseEntity.badRequest()
                    .body("Student must be at least 20 years old");
        }
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        s.setId(id);
        Student updated = repo.save(s);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

