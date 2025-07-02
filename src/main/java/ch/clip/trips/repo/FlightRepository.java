package ch.clip.trips.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.clip.trips.model.Flight;
import ch.clip.trips.model.Employee;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    
    List<Flight> findByEmployee(Employee employee);
    
    List<Flight> findByFromAndTo(String from, String to);
    
    Optional<Flight> findByNumber(String number);
    
    // @Query("SELECT f FROM Flight f WHERE f.from = :departure OR f.to = :destination")
    // List<Flight> findByDepartureOrDestination(@Param("departure") String departure, @Param("destination") String destination);
}
