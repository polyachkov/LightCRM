package ru.polyachkov.LightCRM.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import ru.polyachkov.LightCRM.additions.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Audited
@Entity
@Data // Includes getters, setters, equals, hashCode, toString
@NoArgsConstructor // Generates a constructor without parameters
@AllArgsConstructor // Generates a constructor with parameters for all fields
@Builder // Allows you to use the "Builder" pattern to create objects
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    @Column(nullable = false)
    private LocalDateTime transactionDate;
}
