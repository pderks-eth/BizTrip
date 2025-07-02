package ch.clip.trips.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import ch.clip.trips.model.Meeting;
import ch.clip.trips.repo.MeetingRepository;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class MeetingController {
    
    private static final Logger log = LoggerFactory.getLogger(MeetingController.class);

    @Autowired
    private MeetingRepository meetingRepository;

    /**
     * Get all meetings
     * @return ResponseEntity with list of meetings
     */
    @GetMapping("/meetings")
    public ResponseEntity<List<Meeting>> getAllMeetings() {
        log.info("Fetching all meetings");
        List<Meeting> meetings = (List<Meeting>) meetingRepository.findAll();
        
        if (meetings.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(meetings); // 200 OK
    }

    /**
     * Get a meeting by ID
     * @param id the meeting ID
     * @return ResponseEntity with the meeting or 404 if not found
     */
    @GetMapping("/meetings/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) {
        Optional<Meeting> meeting = meetingRepository.findById(id);
        return meeting.map(m -> ResponseEntity.ok(m))
                     .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    /**
     * Create a new meeting
     * @param newMeeting the meeting to create
     * @return ResponseEntity with the created meeting
     */
    @PostMapping("/meetings")
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting newMeeting) {
        try {
            Meeting savedMeeting = meetingRepository.save(newMeeting);
            log.info("Created new meeting with ID: {}", savedMeeting.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMeeting); // 201 Created
        } catch (Exception e) {
            log.error("Error creating meeting: {}", e.getMessage());
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
    }

    /**
     * Update an existing meeting
     * @param newMeeting the updated meeting data
     * @param id the meeting ID
     * @return ResponseEntity with the updated meeting
     */
    @PutMapping("/meetings/{id}")
    public ResponseEntity<Meeting> updateMeeting(@RequestBody Meeting newMeeting, @PathVariable Long id) {
        try {
            Meeting updatedMeeting = meetingRepository.findById(id)
                .map(meeting -> {
                    meeting.setName(newMeeting.getName());
                    meeting.setDescription(newMeeting.getDescription());
                    if (newMeeting.getBusinessTrip() != null) {
                        meeting.setBusinessTrip(newMeeting.getBusinessTrip());
                    }
                    return meetingRepository.save(meeting);
                })
                .orElseGet(() -> {
                    newMeeting.setId(id);
                    return meetingRepository.save(newMeeting);
                });
                
            log.info("Updated meeting with ID: {}", id);
            return ResponseEntity.ok(updatedMeeting); // 200 OK
        } catch (Exception e) {
            log.error("Error updating meeting with ID {}: {}", id, e.getMessage());
            return ResponseEntity.badRequest().build(); // 400 Bad Request
        }
    }

    /**
     * Search meetings by name (changed from title)
     * @param name the name to search for
     * @return ResponseEntity with matching meetings
     */
    @GetMapping("/meetings/search/{name}")
    public ResponseEntity<List<Meeting>> searchMeetingsByName(@PathVariable String name) {
        List<Meeting> meetings = meetingRepository.findByName(name);
        if (meetings.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(meetings); // 200 OK
    }

    /**
     * Delete a meeting by ID
     * @param id the meeting ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/meetings/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        if (!meetingRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        
        try {
            meetingRepository.deleteById(id);
            log.info("Deleted meeting with ID: {}", id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            log.error("Error deleting meeting with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    /**
     * Delete all meetings
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/meetings")
    public ResponseEntity<Void> deleteAllMeetings() {
        try {
            meetingRepository.deleteAll();
            log.info("Deleted all meetings");
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (Exception e) {
            log.error("Error deleting all meetings: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
}