package ch.clip.trips.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.clip.trips.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    Optional<Employee> findByName(String name);
    
    // @Query("SELECT e FROM Employee e WHERE e.title = :title")
    // java.util.List<Employee> findByTitle(@Param("title") String title);
}
