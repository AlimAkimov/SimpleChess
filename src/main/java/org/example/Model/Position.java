package org.example.Model;

import java.util.Objects;

public class Position {
    private final int col;
    private final int row;


    public Position(int col,int row) {
        if (!isValidPosition(col, row)) {
            throw new IllegalArgumentException("Invalid position: row=" + row + ", col=" + col);
        }
        this.row = row;
        this.col = col;
    }

    public static boolean isValidPosition(int col, int row) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        char colLetter = (char) ('a' + col); // col=1 → 'a', col=2 → 'b'
        int rowNumber = row + 1; // row=1 → 1, row=8 → 8
        return "" + colLetter + rowNumber;
    }

    public int getRow() {
        return row;
    }


    public int getCol() {
        return col;
    }
}
