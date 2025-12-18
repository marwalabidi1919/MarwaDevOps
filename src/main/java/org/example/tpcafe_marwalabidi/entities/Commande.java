package org.example.tpcafe_marwalabidi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table( name = "Commande")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode

public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommande;

    private LocalDate dateCommande;

    private Double totalCommande;

    @Enumerated(EnumType.STRING)
    private StatusCommande statusCommande;


    // Relation avec Client
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    // Relation avec DetailCommande
    @OneToMany(mappedBy = "commande")
    private List<Details_Commande> detailsCommande;
}
