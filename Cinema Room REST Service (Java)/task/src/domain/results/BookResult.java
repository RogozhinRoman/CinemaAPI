package domain.results;

import domain.models.Seat;

import java.util.UUID;

public class BookResult {
    public UUID bookingId;
    public Boolean isSuccess;
    public String errorMessage;
    public Seat seat;

    private BookResult(Boolean isSuccess, String errorMessage, Seat seat, UUID bookingId) {
        this.isSuccess = isSuccess;
        this.errorMessage = errorMessage;
        this.seat = seat;
        this.bookingId = bookingId;
    }

    public static BookResult createSuccess(Seat bookedSeat, UUID bookingId) {
        return new BookResult(true, null, bookedSeat, bookingId);
    }

    public static BookResult createError(String errorMessage) {
        return new BookResult(false, errorMessage, null, null);
    }
}
