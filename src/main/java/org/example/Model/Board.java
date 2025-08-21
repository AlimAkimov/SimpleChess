package org.example.Model;

import java.util.List;

public interface Board {
    Figure getFigure(Position pos);

    boolean isEmpty(Position position);

    void setup();

    void move(Move move);

    List<Figure> getFigures(Color color);
}
