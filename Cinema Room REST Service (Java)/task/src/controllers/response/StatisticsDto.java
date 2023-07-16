package controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatisticsDto {
    @JsonProperty("current_income")
    public Integer currentIncome;
    @JsonProperty("number_of_available_seats")
    public Integer availableSeatsNumber;
    @JsonProperty("number_of_purchased_tickets")
    public Integer purchasedTicketsNumber;
}
