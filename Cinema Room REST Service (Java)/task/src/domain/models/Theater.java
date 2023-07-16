package domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Theater {
    public Integer totalRows;
    public Integer totalColumns;
    public List<Seat> seats;

    public Theater(int rows, int columns) {
        totalRows = rows;
        totalColumns = columns;

        seats = new ArrayList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                seats.add(new Seat(i + 1, j + 1));
            }
    }

    public Optional<Seat> getSeat(int row, int column) {
        return seats.stream().filter(s -> s.row == row && s.column == column).findFirst();
    }
}
