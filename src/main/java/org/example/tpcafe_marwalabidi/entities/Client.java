package org.example.tpcafe_marwalabidi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;



    @OneToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

    @OneToOne(mappedBy = "client")
    private CarteFidelite carteFidelite;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;
}