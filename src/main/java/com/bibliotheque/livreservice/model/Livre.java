package com.bibliotheque.livreservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "livres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String auteur;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(nullable = false)
    private Integer nombreExemplaires;

    @Column(nullable = false)
    private Boolean disponible;
}