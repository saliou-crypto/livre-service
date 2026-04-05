package com.bibliotheque.livreservice.repository;

import com.bibliotheque.livreservice.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    Optional<Livre> findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);
}