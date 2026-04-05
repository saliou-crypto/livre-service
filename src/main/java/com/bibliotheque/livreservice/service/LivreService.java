package com.bibliotheque.livreservice.service;

import com.bibliotheque.livreservice.dto.LivreDTO;
import com.bibliotheque.livreservice.model.Livre;
import com.bibliotheque.livreservice.repository.LivreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivreService {

    private final LivreRepository livreRepository;

    public List<LivreDTO> findAll() {
        return livreRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LivreDTO findById(Long id) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'id : " + id));
        return toDTO(livre);
    }

    public LivreDTO save(LivreDTO dto) {
        if (livreRepository.existsByIsbn(dto.getIsbn())) {
            throw new RuntimeException("Un livre avec cet ISBN existe déjà : " + dto.getIsbn());
        }
        Livre livre = toEntity(dto);
        return toDTO(livreRepository.save(livre));
    }

    public LivreDTO update(Long id, LivreDTO dto) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'id : " + id));
        livre.setTitre(dto.getTitre());
        livre.setAuteur(dto.getAuteur());
        livre.setIsbn(dto.getIsbn());
        livre.setNombreExemplaires(dto.getNombreExemplaires());
        livre.setDisponible(dto.getDisponible());
        return toDTO(livreRepository.save(livre));
    }

    public void delete(Long id) {
        if (!livreRepository.existsById(id)) {
            throw new RuntimeException("Livre non trouvé avec l'id : " + id);
        }
        livreRepository.deleteById(id);
    }

    // Méthode utilisée par emprunt-service via Feign
    public boolean verifierDisponibilite(Long id) {
        Livre livre = livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'id : " + id));
        return livre.getDisponible() && livre.getNombreExemplaires() > 0;
    }

    // Mappers
    private LivreDTO toDTO(Livre livre) {
        return LivreDTO.builder()
                .id(livre.getId())
                .titre(livre.getTitre())
                .auteur(livre.getAuteur())
                .isbn(livre.getIsbn())
                .nombreExemplaires(livre.getNombreExemplaires())
                .disponible(livre.getDisponible())
                .build();
    }

    private Livre toEntity(LivreDTO dto) {
        return Livre.builder()
                .titre(dto.getTitre())
                .auteur(dto.getAuteur())
                .isbn(dto.getIsbn())
                .nombreExemplaires(dto.getNombreExemplaires())
                .disponible(dto.getDisponible())
                .build();
    }
}