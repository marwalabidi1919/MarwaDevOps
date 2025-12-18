package org.example.tpcafe_marwalabidi.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promotion")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode

public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPromotion;
    private String pourcentagePromo;
    @Temporal(TemporalType.DATE)
    private LocalDate dateDebutPromo;
    @Temporal(TemporalType.DATE)
    private LocalDate dateFinPromo;

    @ManyToMany
    @JoinTable(
        name = "promotion_article",
        joinColumns = @JoinColumn(name = "promotion_id"),
        inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles;
}