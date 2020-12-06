package _2020.day5;

import lombok.Value;

@Value
public class Seat {
    int row;
    int column;

    public int getId() {
        return row * 8 + column;
    }
}
