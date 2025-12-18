package org.example.tpcafe_marwalabidi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Entity
@Table(name= "article")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode

public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    private String nomArticle;

    private Double prixArticle;

    @Enumerated(EnumType.STRING)
    private TypeArticle typeArticle; // Utilise l'enum CategorieProduit

    @OneToMany(mappedBy = "article")
    private List<Details_Commande> detailsCommande;

    // Relation avec Promotion (many-to-many)
    @ManyToMany(mappedBy = "articles")
    private List<Promotion> promotions;
}