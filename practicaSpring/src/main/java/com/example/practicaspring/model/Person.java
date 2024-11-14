package com.example.practicaspring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
        public class Person {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Integer id;

            private String name;
            private String email;
            private  String password;
            private  String role;

            @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="person")
            private Set<Invoice> invoices;

        }
