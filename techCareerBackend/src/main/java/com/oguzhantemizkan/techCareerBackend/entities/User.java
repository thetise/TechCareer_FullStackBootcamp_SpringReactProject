package com.oguzhantemizkan.techCareerBackend.entities;

import jakarta.persistence.*;
import lombok.Data;

// Bu Java sınıfı, bir kullanıcıyı temsil eden bir JPA (Java Persistence API) varlığıdır.
@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String userName;
    String password;
}
