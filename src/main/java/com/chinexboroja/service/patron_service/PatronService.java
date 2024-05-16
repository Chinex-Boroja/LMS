package com.chinexboroja.service.patron_service;


import com.chinexboroja.dto.patron.PatronRequest;
import com.chinexboroja.models.Patron;
import java.util.List;
import java.util.Optional;

public interface PatronService {

    List<Patron> getAllPatrons();

    Optional<Patron> getPatronById(Long patronId);

    Patron addNewPatron(PatronRequest patronRequest);

    Patron updatePatron(Long patronId, PatronRequest patronRequest);

    void deletePatron(Long patronId);
}
