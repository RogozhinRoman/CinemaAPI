package domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Seat {
    @JsonProperty("row")
    public Integer row;
    @JsonProperty("column")
    public Integer column;
    @JsonProperty("price")
    public Integer price;

    public Seat(Integer row, Integer column){
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 10 : 8;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(row, seat.row) && Objects.equals(column, seat.column) && Objects.equals(price, seat.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, price);
    }
}
