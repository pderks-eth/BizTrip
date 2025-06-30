package ch.clip.trips;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.clip.trips.model.BusinessTrip;
import ch.clip.trips.model.Meeting;
import ch.clip.trips.repo.BusinessTripRepository;
import ch.clip.trips.repo.MeetingRepository;

@SpringBootApplication
public class BusinessTripsBackendApplication {
	private static final Logger log = LoggerFactory.getLogger(BusinessTripsBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BusinessTripsBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(BusinessTripRepository businessTripRepository,
			MeetingRepository meetingRepository) {
		// https://spring.io/guides/gs/accessing-data-jpa/
		return (args) -> {

			// save a couple of BusinessTrips
			BusinessTrip bt01 = new BusinessTrip(1L, "BT01",
					"San Francisco World Trade Center on new Server/IOT/Client ", LocalDateTime.of(2021, 2, 13, 9, 00),
					LocalDateTime.of(2021, 2, 15, 16, 56));
			BusinessTrip bt02 = new BusinessTrip(2L, "BT02", "Santa Clara Halley on new Server/IOT/Client",
					LocalDateTime.of(2021, 6, 23, 9, 00), LocalDateTime.of(2021, 6, 27, 16, 56));
			BusinessTrip bt03 = new BusinessTrip(3L, "BT03", "San Cose City Halley on Docker/IOT/Client",
					LocalDateTime.of(2021, 12, 13, 9, 00), LocalDateTime.of(2021, 12, 15, 16, 56));

			BusinessTrip bt04 = new BusinessTrip(4L, "BT04", "London Tech Summit for Cloud Solutions",
					LocalDateTime.of(2022, 1, 10, 10, 0), LocalDateTime.of(2022, 1, 13, 18, 30));
			BusinessTrip bt05 = new BusinessTrip(5L, "BT05", "Munich Security Expo for Networking",
					LocalDateTime.of(2022, 3, 5, 8, 0), LocalDateTime.of(2022, 3, 7, 17, 45));
			BusinessTrip bt06 = new BusinessTrip(6L, "BT06", "Zurich Cloud Conference on Migration",
					LocalDateTime.of(2022, 4, 21, 9, 30), LocalDateTime.of(2022, 4, 25, 19, 0));
			BusinessTrip bt07 = new BusinessTrip(7L, "BT07", "Paris FinTech Forum customer meetings",
					LocalDateTime.of(2022, 7, 11, 10, 0), LocalDateTime.of(2022, 7, 15, 16, 0));
			BusinessTrip bt08 = new BusinessTrip(8L, "BT08", "Berlin DevOps Workshop with partners",
					LocalDateTime.of(2022, 8, 18, 12, 15), LocalDateTime.of(2022, 8, 22, 15, 0));
			BusinessTrip bt09 = new BusinessTrip(9L, "BT09", "New York investor roadshow",
					LocalDateTime.of(2022, 10, 2, 9, 0), LocalDateTime.of(2022, 10, 5, 16, 30));
			BusinessTrip bt10 = new BusinessTrip(10L, "BT10", "Tokyo hardware vendor negotiations",
					LocalDateTime.of(2022, 11, 14, 11, 0), LocalDateTime.of(2022, 11, 18, 18, 0));
			BusinessTrip bt11 = new BusinessTrip(11L, "BT11", "Austin startup accelerator meetings",
					LocalDateTime.of(2023, 2, 10, 9, 0), LocalDateTime.of(2023, 2, 12, 17, 0));
			BusinessTrip bt12 = new BusinessTrip(12L, "BT12", "Barcelona IoT showcase",
					LocalDateTime.of(2023, 5, 3, 8, 30), LocalDateTime.of(2023, 5, 7, 20, 0));
			BusinessTrip bt13 = new BusinessTrip(13L, "BT13", "Dubai logistics client visits",
					LocalDateTime.of(2023, 9, 9, 10, 15), LocalDateTime.of(2023, 9, 14, 17, 30));
			businessTripRepository.save(bt01);
			businessTripRepository.save(bt02);
			businessTripRepository.save(bt03);
			businessTripRepository.save(bt04);
			businessTripRepository.save(bt05);
			businessTripRepository.save(bt06);
			businessTripRepository.save(bt07);
			businessTripRepository.save(bt08);
			businessTripRepository.save(bt09);
			businessTripRepository.save(bt10);
			businessTripRepository.save(bt11);
			businessTripRepository.save(bt12);
			businessTripRepository.save(bt13);

			// save a couple of meetings

			Meeting one = new Meeting(1L, "One Conference", "Key Note on One Conference", bt01);
			Meeting zero = new Meeting(2L, "Zero Conference", "Workshop Zero on One Conference", bt01);
			Meeting handsOn = new Meeting(3L, "One Conference", "HandsOn on One Conference", bt02);
			Meeting workOn = new Meeting(4L, "One Conference", "Key Note on One Conference", bt02);
			Meeting stayTuned = new Meeting(5L, "One Conference", "Key Note on One Conference", bt03);

			meetingRepository.save(one);
			meetingRepository.save(zero);
			meetingRepository.save(handsOn);
			meetingRepository.save(workOn);
			meetingRepository.save(stayTuned);

			// List<Trips>

			List<BusinessTrip> wishTrips = new ArrayList<BusinessTrip>();

			wishTrips.add(bt01);
			wishTrips.add(bt02);
			wishTrips.add(bt03);
			wishTrips.add(bt04);
			wishTrips.add(bt05);
			wishTrips.add(bt06);
			wishTrips.add(bt07);
			wishTrips.add(bt08);
			wishTrips.add(bt09);
			wishTrips.add(bt10);
			wishTrips.add(bt11);
			wishTrips.add(bt12);
			wishTrips.add(bt13);

		};
	}

}
