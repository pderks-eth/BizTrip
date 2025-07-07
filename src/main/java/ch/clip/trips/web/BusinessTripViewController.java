package ch.clip.trips.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.clip.trips.model.BusinessTrip;
import ch.clip.trips.repo.BusinessTripRepository;

@Controller
public class BusinessTripViewController {
    @Autowired
    private BusinessTripRepository businessTripRepository;

    @GetMapping("/trips")
    public String showTrips(Model model) {
        List<BusinessTrip> trips = businessTripRepository.findAll();
        model.addAttribute("trips", trips);
        return "trips"; // returns trips.html template
    }
}
