package controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import domain.models.Seat;

public class ReturnTicketResponse {
    @JsonProperty("returned_ticket")
    public Seat seat;
}
