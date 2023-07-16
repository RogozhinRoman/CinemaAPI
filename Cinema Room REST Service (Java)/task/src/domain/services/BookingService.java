package domain.services;

import controllers.response.StatisticsDto;
import domain.models.Seat;
import domain.models.Theater;
import domain.results.BookResult;
import domain.results.CancelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {
    private final Theater theater;
    private final Map<UUID, Seat> bookedSeats = new HashMap<>();

    @Autowired
    public BookingService(Theater theater) {
        this.theater = theater;
    }

    public List<Seat> getAvailableSeats() {
        Set<Seat> bookedSeats = new HashSet<>(this.bookedSeats.values());
        return theater.seats.stream().filter(s -> !bookedSeats.contains(s)).toList();
    }

    public StatisticsDto getBookingStatistics() {
        List<Seat> bookedSeats = theater.seats.stream()
                .filter(this.bookedSeats.values()::contains)
                .toList();
        var currentIncome = 0;
        for (Seat bookedSeat : bookedSeats) {
            currentIncome += bookedSeat.price;
        }

        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.currentIncome = currentIncome;
        statisticsDto.purchasedTicketsNumber = bookedSeats.size();
        statisticsDto.availableSeatsNumber = theater.seats.size() - bookedSeats.size();
        return statisticsDto;
    }


    public BookResult tryBookSeat(int rows, int column) {
        if (rows > theater.totalRows || rows < 0 || column > theater.totalColumns || column < 0) {
            return BookResult.createError("The number of a row or a column is out of bounds!");
        }
        Optional<Seat> seat = theater.getSeat(rows, column);
        if (seat.isEmpty() || bookedSeats.containsValue(seat.get())) {
            return BookResult.createError("The ticket has been already purchased!");
        }

        Seat foundSeat = seat.get();
        UUID bookingId = UUID.randomUUID();
        bookedSeats.put(bookingId, foundSeat);
        return BookResult.createSuccess(seat.get(), bookingId);
    }

    public CancelResult tryCancelBooking(UUID token) {
        if (!bookedSeats.containsKey(token)) {
            return CancelResult.createFail("Wrong token!");
        } else {
            Seat seat = bookedSeats.get(token);
            bookedSeats.remove(token);
            return CancelResult.createSuccess(seat);
        }
    }
}
