package ch.clip.trips.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.clip.trips.model.Flight;
import ch.clip.trips.repo.FlightRepository;

@Controller
public class FlightViewController {
    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/flights")
    public String showFlights(Model model) {
        List<Flight> flights = flightRepository.findAll();
        model.addAttribute("flights", flights);
        return "flights"; // returns flights.html template
    }
}
