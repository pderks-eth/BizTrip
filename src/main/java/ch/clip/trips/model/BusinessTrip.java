package ch.clip.trips.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class BusinessTrip implements Serializable {

    // private static final long serialVersionUID = 67027563808382509L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTrip;
    private LocalDateTime endTrip;

    @OneToMany(mappedBy = "businessTrip")
    @JsonManagedReference("businessTrip-meetings")
    private List<Meeting> meetings;

    @ManyToMany
    @JoinTable(name = "businesstrip_employee", joinColumns = @JoinColumn(name = "businesstrip_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @JsonIgnore
    private List<Employee> employees;

    public BusinessTrip() {
        super();
    }

    public BusinessTrip(Long id, String title, String description, LocalDateTime startTrip, LocalDateTime endTrip) {
        this();
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTrip = startTrip;
        this.endTrip = endTrip;
    }

    @Override
    public String toString() {
        return "BusinessTrip [id=" + id + ", title=" + title + ", description=" + description + ", startTrip="
                + startTrip + ", endTrip=" + endTrip + ", meetings=" + meetings + ", employees=" + employees + "]";
    }
}