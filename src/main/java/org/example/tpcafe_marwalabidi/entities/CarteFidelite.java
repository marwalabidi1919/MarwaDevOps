package org.example.tpcafe_marwalabidi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "carteFidelite")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode

public class CarteFidelite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarteFidelite;

    private Integer pointsAccumules;

    private LocalDate dateCreation;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
