package org.example.Model;

public class Move {
    private Position from;
    private Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "from=" + from +
                ", to=" + to +
                '}';
    }

    public Position getFrom() {
        return from;
    }


    public Position getTo() {
        return to;
    }

}
