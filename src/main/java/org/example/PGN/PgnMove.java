package org.example.PGN;

import org.example.Model.Figure;
import org.example.Model.Position;

public class PgnMove {
    private final Figure figure;
    private final Position from;
    private final Position to;
    private final boolean capture;

    public PgnMove(Figure figure, Position from, Position to, boolean capture) {
        this.figure = figure;
        this.from = from;
        this.to = to;
        this.capture = capture;
    }

    public Figure getFigure() { return figure; }
    public Position getFrom() { return from; }
    public Position getTo() { return to; }
    public boolean isCapture() { return capture; }
}

