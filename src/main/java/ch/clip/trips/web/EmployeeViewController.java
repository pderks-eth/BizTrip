package ch.clip.trips.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.clip.trips.model.Employee;
import ch.clip.trips.repo.EmployeeRepository;

@Controller
public class EmployeeViewController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public String showEmployees(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees"; // returns employees.html template
    }
}
