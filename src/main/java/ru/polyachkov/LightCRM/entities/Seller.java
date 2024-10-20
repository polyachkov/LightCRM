package ru.polyachkov.LightCRM.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Data // Includes getters, setters, equals, hashCode, toString
@NoArgsConstructor // Generates a constructor without parameters
@AllArgsConstructor // Generates a constructor with parameters for all fields
@Builder // Allows you to use the "Builder" pattern to create objects
public class Seller
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contactInfo;

    @Column(nullable = false)
    private LocalDateTime registrationDate;
}
