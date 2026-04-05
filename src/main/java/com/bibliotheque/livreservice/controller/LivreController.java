package com.bibliotheque.livreservice.controller;

import com.bibliotheque.livreservice.dto.LivreDTO;
import com.bibliotheque.livreservice.service.LivreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
@RequiredArgsConstructor
public class LivreController {

    private final LivreService livreService;

    @GetMapping
    public ResponseEntity<List<LivreDTO>> findAll() {
        return ResponseEntity.ok(livreService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivreDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(livreService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LivreDTO> save(@RequestBody LivreDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livreService.save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivreDTO> update(@PathVariable Long id, @RequestBody LivreDTO dto) {
        return ResponseEntity.ok(livreService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livreService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint appelé par emprunt-service
    @GetMapping("/{id}/disponibilite")
    public ResponseEntity<Boolean> verifierDisponibilite(@PathVariable Long id) {
        return ResponseEntity.ok(livreService.verifierDisponibilite(id));
    }
}