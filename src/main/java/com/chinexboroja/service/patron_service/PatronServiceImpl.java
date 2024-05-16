package com.chinexboroja.service.patron_service;

import com.chinexboroja.dto.patron.PatronRequest;
import com.chinexboroja.exceptions.BadRequestException;
import com.chinexboroja.exceptions.NoContentException;
import com.chinexboroja.exceptions.NotFoundException;
import com.chinexboroja.models.Patron;
import com.chinexboroja.repositories.PatronRepository;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PatronServiceImpl implements PatronService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatronServiceImpl.class);
    private final PatronRepository patronRepository;

    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }


    @Override
    public List<Patron> getAllPatrons() {
        final List<Patron> patrons = patronRepository.findAll();

        if (patrons.isEmpty()) {
            throw new NoContentException("Patrons not found");
        }
        return patrons;
    }

    @Override
    public Optional<Patron> getPatronById(Long patronId) {
        return Optional.of(patronRepository.findById(patronId)
            .orElseThrow(() -> new NotFoundException("Patron not found with id " + patronId)));
    }

    @Override
    public Patron addNewPatron(PatronRequest patronRequest) {
        if (isPatronAlreadyExists(patronRequest)) {
            throw new BadRequestException("Patron with email " + patronRequest.getEmail() + " already exists");
        }

        final Patron patron = new Patron();
        patron.setFirstName(patronRequest.getFirstName());
        patron.setLastName(patronRequest.getLastName());
        patron.setAddress(patronRequest.getAddress());
        patron.setPhoneNumber(patronRequest.getPhoneNumber());
        patron.setEmail(patronRequest.getEmail());

        return patronRepository.save(patron);
    }

    @Override
    public Patron updatePatron(Long patronId, PatronRequest patronRequest) {

        Optional<Patron> patronOpt = getPatronById(patronId);

        if (patronOpt.isEmpty()) {
            throw new NotFoundException("Patron not found with id " + patronId);
        }

        Patron patron = patronOpt.get();
        patron.setFirstName(patronRequest.getFirstName());
        patron.setLastName(patronRequest.getLastName());
        patron.setAddress(patronRequest.getAddress());
        patron.setPhoneNumber(patronRequest.getPhoneNumber());
        patron.setEmail(patronRequest.getEmail());

        return patronRepository.save(patron);
    }

    @Override
    public void deletePatron(Long patronId) {

        if (getPatronById(patronId).isEmpty()) {
            LOGGER.info("Delete not successful, Patron not found with id {}", patronId);
        }
        patronRepository.deleteById(patronId);

    }

    private boolean isPatronAlreadyExists(PatronRequest patronRequest) {
        Optional<Patron> existingPatron = patronRepository.findByEmail(patronRequest.getEmail());
        return existingPatron.isPresent();
    }
}
