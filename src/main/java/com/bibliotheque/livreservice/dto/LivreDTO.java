package com.bibliotheque.livreservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivreDTO {
    private Long id;
    private String titre;
    private String auteur;
    private String isbn;
    private Integer nombreExemplaires;
    private Boolean disponible;
}