package com.example.practicaspring.service;

import com.example.practicaspring.model.Person;
import com.example.practicaspring.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;

    public Optional<Person> findByEmailAndPassword(String email, String password) {
        return personRepo.findByEmailAndPassword(email, password);
    }

}
