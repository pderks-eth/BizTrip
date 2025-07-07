package ch.clip.trips.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.clip.trips.model.Flight;
import ch.clip.trips.repo.FlightRepository;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        if (flights.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight newFlight) {
        try {
            Flight saved = flightRepository.save(newFlight);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/flights/{id}")
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight newFlight, @PathVariable Long id) {
        try {
            Flight updated = flightRepository.findById(id)
                .map(flight -> {
                    flight.setNumber(newFlight.getNumber());
                    flight.setFrom(newFlight.getFrom());
                    flight.setTo(newFlight.getTo());
                    if (newFlight.getEmployee() != null) {
                        flight.setEmployee(newFlight.getEmployee());
                    }
                    return flightRepository.save(flight);
                })
                .orElseGet(() -> {
                    newFlight.setId(id);
                    return flightRepository.save(newFlight);
                });
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        if (!flightRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            flightRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Cannot delete flight. It is referenced by other entities.");
        }
    }
}
