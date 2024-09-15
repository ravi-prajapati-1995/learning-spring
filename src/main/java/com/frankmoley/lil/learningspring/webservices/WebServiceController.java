package com.frankmoley.lil.learningspring.webservices;

import com.frankmoley.lil.learningspring.business.ReservationService;
import com.frankmoley.lil.learningspring.business.RoomReservation;
import com.frankmoley.lil.learningspring.data.Guest;
import com.frankmoley.lil.learningspring.data.Room;
import com.frankmoley.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
public class WebServiceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;

    public WebServiceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }

    @GetMapping(path = "/reservations")
    @ResponseStatus(OK)
    public List<RoomReservation> getAll(@RequestParam(name = "dateString", required = false) String dateString) {
        Date date = dateUtils.createDateFromDateString(dateString);
        return reservationService.getRoomReservationsForDate(date);
    }

    @GetMapping(path = "/guest")
    @ResponseStatus(OK)
    public List<Guest> getAllGuests() {
        return reservationService.getHotelGuests();
    }

    @PostMapping(path = "/guest")
    @ResponseStatus(CREATED)
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest, HttpServletRequest request) {
        Guest guest1 = reservationService.addGuest(guest);
        final var httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(request.getRequestURI() + "/" + guest1.getGuestId()));
        return new ResponseEntity<>(null, httpHeaders, CREATED);
    }

    @GetMapping(path = "/room")
    @ResponseStatus(OK)
    public Iterable<Room> getAllRoom() {
        return reservationService.getAllRooms();
    }

}
