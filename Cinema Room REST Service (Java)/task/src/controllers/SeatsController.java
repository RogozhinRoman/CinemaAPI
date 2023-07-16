package controllers;

import controllers.dtos.PurchaseDto;
import controllers.dtos.TheaterDto;
import controllers.request.BookSeatRequest;
import controllers.request.ReturnTicketRequest;
import controllers.response.ReturnTicketResponse;
import controllers.response.StatisticsDto;
import domain.results.BookResult;
import domain.services.BookingService;
import domain.results.CancelResult;
import domain.models.Theater;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class SeatsController {
    private final BookingService bookingService;
    private final Theater theater;

    @Autowired
    public SeatsController(BookingService bookingService, Theater theater) {
        this.bookingService = bookingService;
        this.theater = theater;
    }

    @RequestMapping("/seats")
    public TheaterDto getSeats() {
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.totalRows = theater.totalRows;
        theaterDto.totalColumns = theater.totalColumns;
        theaterDto.seats = bookingService.getAvailableSeats();

        return theaterDto;
    }

    @RequestMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody BookSeatRequest bookSeatRequest) {
        BookResult bookResult = bookingService.tryBookSeat(bookSeatRequest.row, bookSeatRequest.column);

        if (bookResult.isSuccess) {
            PurchaseDto purchaseDto = new PurchaseDto();
            purchaseDto.seat = bookResult.seat;
            purchaseDto.bookingId = bookResult.bookingId;
            return new ResponseEntity<>(purchaseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", bookResult.errorMessage), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody ReturnTicketRequest request) {
        CancelResult cancelResult = bookingService.tryCancelBooking(request.token);

        if (cancelResult.isSuccess){
            ReturnTicketResponse response = new ReturnTicketResponse();
            response.seat = cancelResult.returnedTicket;
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", cancelResult.errorMessage), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam @Nullable String password){
        if (password == null || !password.equals("super_secret")) {
            return new ResponseEntity<>(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        } else {
            StatisticsDto bookingStatistics = bookingService.getBookingStatistics();
            return new ResponseEntity<>(bookingStatistics, HttpStatus.OK);
        }
    }
}
