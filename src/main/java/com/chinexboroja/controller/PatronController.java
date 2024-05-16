package com.chinexboroja.controller;

import com.chinexboroja.dto.patron.PatronRequest;
import com.chinexboroja.dto.patron.Response;
import com.chinexboroja.models.Patron;
import com.chinexboroja.service.patron_service.PatronService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/patrons")
public class PatronController {

    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<Response<?>> getAllPatrons() {
        List<Patron> patrons = patronService.getAllPatrons();
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Patrons retrieved successfully",
                patrons),
            HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getPatronById(@PathVariable Long id) {
        final Optional<Patron> patron = patronService.getPatronById(id);
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Patron retrieved successfully",
                patron),
            HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Response<?>> addNewPatron(@Valid @RequestBody PatronRequest patronRequest) {
        final Patron patron = patronService.addNewPatron(patronRequest);
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Patron added successfully",
                patron),
            HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> updatePatron(@PathVariable Long id, @Valid @RequestBody PatronRequest patronRequest) {
        Patron updatedPatron = patronService.updatePatron(id, patronRequest);
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Patron updated successfully",
                updatedPatron),
            HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
        return new ResponseEntity<>(
            new Response<>(
                true,
                "Patron deleted successfully",
                null),
            HttpStatus.OK);
    }
}
