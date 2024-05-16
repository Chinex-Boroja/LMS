package com.chinexboroja.service.patron_service;


import com.chinexboroja.dto.patron.PatronRequest;
import com.chinexboroja.models.Patron;
import java.util.List;
import java.util.Optional;

public interface PatronService {

    // GET /api/patrons: Retrieve a list of all patrons.
    //● GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
    //● POST /api/patrons: Add a new patron to the system.
    //● PUT /api/patrons/{id}: Update an existing patron's information.
    //● DELETE /api/patrons/{id}: Remove a patron from the system.
    List<Patron> getAllPatrons();

    Optional<Patron> getPatronById(Long patronId);

    Patron addNewPatron(PatronRequest patronRequest);

    Patron updatePatron(Long patronId, PatronRequest patronRequest);

    void deletePatron(Long patronId);
}
