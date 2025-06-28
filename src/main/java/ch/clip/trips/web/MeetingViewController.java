package ch.clip.trips.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ch.clip.trips.model.Meeting;
import ch.clip.trips.repo.MeetingRepository;

@Controller
public class MeetingViewController {

    @Autowired
    private MeetingRepository meetingRepository;

    @GetMapping("/meetings")
    public String showMeetings(Model model) {
        List<Meeting> meetings = (List<Meeting>) meetingRepository.findAll();
        model.addAttribute("meetings", meetings);
        return "meetings";
    }
}
