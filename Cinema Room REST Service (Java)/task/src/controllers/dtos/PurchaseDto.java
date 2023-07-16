package controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import domain.models.Seat;

import java.util.UUID;

public class PurchaseDto {
    @JsonProperty("token")
    public UUID bookingId;
    @JsonProperty("ticket")
    public Seat seat;
}
