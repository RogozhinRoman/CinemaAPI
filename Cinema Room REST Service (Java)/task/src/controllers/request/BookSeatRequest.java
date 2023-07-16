package controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookSeatRequest {
    @JsonProperty("row")
    public Integer row;
    @JsonProperty("column")
    public Integer column;
}
