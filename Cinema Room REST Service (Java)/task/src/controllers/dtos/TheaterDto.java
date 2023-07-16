package controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import domain.models.Seat;

import java.util.List;

public class TheaterDto {
    @JsonProperty("total_rows")
    public Integer totalRows;
    @JsonProperty("total_columns")
    public Integer totalColumns;
    @JsonProperty("available_seats")
    public List<Seat> seats;
}
