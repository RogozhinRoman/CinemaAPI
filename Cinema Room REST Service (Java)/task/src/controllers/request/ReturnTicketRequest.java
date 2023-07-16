package controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ReturnTicketRequest {
    @JsonProperty("token")
    public UUID token;
}
