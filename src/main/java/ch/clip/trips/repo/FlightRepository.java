package ch.clip.trips.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.clip.trips.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
