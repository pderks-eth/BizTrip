package ch.clip.trips.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.clip.trips.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
