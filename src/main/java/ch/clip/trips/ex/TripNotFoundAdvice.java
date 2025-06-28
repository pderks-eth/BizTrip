package ch.clip.trips.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TripNotFoundAdvice {

	@ResponseBody
        @ExceptionHandler(TripNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String tripNotFoundHandler(TripNotFoundException ex) {
		return ex.getMessage();
	}
}
