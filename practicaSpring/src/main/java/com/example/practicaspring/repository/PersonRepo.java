package com.example.practicaspring.repository;

import com.example.practicaspring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

    Optional<Person> findByEmailAndPassword(String email, String password);

}
