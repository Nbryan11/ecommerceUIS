package com.example.practicaspring.controller;

import com.example.practicaspring.model.Person;
import com.example.practicaspring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/login")
    public ResponseEntity<Person> login(@RequestBody Person person) {
        Optional<Person> foundPerson = personService.findByEmailAndPassword(person.getEmail(), person.getPassword());
        if (foundPerson.isPresent()) {
            return ResponseEntity.ok(foundPerson.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
