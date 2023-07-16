package domain.results;

import domain.models.Seat;

public class CancelResult {
    public final String errorMessage;
    public final boolean isSuccess;
    public final Seat returnedTicket;

    private CancelResult(String errorMessage, boolean isSuccess, Seat returnedTicket) {
        this.errorMessage = errorMessage;
        this.isSuccess = isSuccess;
        this.returnedTicket = returnedTicket;
    }

    public static CancelResult createSuccess(Seat returnedTicket) {
        return new CancelResult(null, true, returnedTicket);
    }

    public static CancelResult createFail(String errorMessage) {
        return new CancelResult(errorMessage, false, null);
    }
}
