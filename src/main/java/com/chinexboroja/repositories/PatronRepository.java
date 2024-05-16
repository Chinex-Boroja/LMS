package com.chinexboroja.repositories;

import com.chinexboroja.models.Patron;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    Optional<Patron> findByEmail(String email);
}
