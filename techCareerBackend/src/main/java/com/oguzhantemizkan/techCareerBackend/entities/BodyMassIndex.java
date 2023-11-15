package com.oguzhantemizkan.techCareerBackend.entities;

import jakarta.persistence.*;
import lombok.Data;

// Bu Java sınıfı, bir vücut kitle indeksi (BMI) örneğini temsil eden bir JPA (Java Persistence API) varlığıdır
@Data
@Entity
@Table(name = "bmi")
public class BodyMassIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double height;
    private double weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    private double bodyMassIndexValue;
    private String result;
}
