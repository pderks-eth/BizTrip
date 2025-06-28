package ch.clip.trips.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.clip.trips.model.BusinessTrip;
import ch.clip.trips.repo.BusinessTripRepository;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class BusinessTripController {

    @Autowired
    private BusinessTripRepository tripRepository;

    /**
     * Get all business trips
     * @return ResponseEntity with list of business trips
     */
    @GetMapping("/trips")
    public ResponseEntity<List<BusinessTrip>> getAllTrips() {
        List<BusinessTrip> trips = tripRepository.findAll();
        if (trips.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(trips); // 200 OK
    }

    /**
     * Get a business trip by ID
     * @param id the trip ID
     * @return ResponseEntity with the business trip or 404 if not found
     */
    @GetMapping("/trips/{id}")
    public ResponseEntity<BusinessTrip> getTripById(@PathVariable Long id) {
        Optional<BusinessTrip> trip = tripRepository.findById(id);
        return trip.map(t -> ResponseEntity.ok(t))
                  .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    /**
     * Create a new business trip
     * @param newTrip the business trip to create
     * @return ResponseEntity with the created business trip
     */
    @PostMapping("/trips")
    public ResponseEntity<BusinessTrip> createTrip(@RequestBody BusinessTrip newTrip) {
        try {
            BusinessTrip savedTrip = tripRepository.save(newTrip);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTrip); // 201 Created
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
    }

    /**
     * Update an existing business trip or create a new one
     * @param newTrip the updated business trip data
     * @param id the trip ID
     * @return ResponseEntity with the updated business trip
     */
    @PutMapping("/trips/{id}")
    public ResponseEntity<BusinessTrip> updateTrip(@RequestBody BusinessTrip newTrip, @PathVariable Long id) {
        try {
            BusinessTrip updatedTrip = tripRepository.findById(id)
                .map(trip -> {
                    trip.setTitle(newTrip.getTitle());
                    trip.setDescription(newTrip.getDescription());
                    trip.setStartTrip(newTrip.getStartTrip());
                    trip.setEndTrip(newTrip.getEndTrip());
                    return tripRepository.save(trip);
                })
                .orElseGet(() -> {
                    newTrip.setId(id);
                    return tripRepository.save(newTrip);
                });
            return ResponseEntity.ok(updatedTrip); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
    }

    /**
     * Delete a business trip by ID
     * @param id the trip ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/trips/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        if (!tripRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        
        try {
            tripRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    /**
     * Search business trips by title
     * @param title the title to search for
     * @return ResponseEntity with matching business trips
     */
    @GetMapping("/trips/search/{title}")
    public ResponseEntity<List<BusinessTrip>> searchTripsByTitle(@PathVariable String title) {
        List<BusinessTrip> trips = tripRepository.findByTitle(title);
        if (trips.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(trips); // 200 OK
    }
}
