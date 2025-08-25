package org.example.PGN;

import org.example.Model.ChessFigures.Pawn;
import org.example.Model.Position;

public class PgnFormatter {

    public static String format(PgnMove move) {
        StringBuilder sb = new StringBuilder();

        if (!(move.getFigure() instanceof Pawn)) {
            sb.append(Character.toUpperCase(move.getFigure().getSymbol()));
        }

        if (move.isCapture()) {
            if (move.getFigure() instanceof Pawn) {
                sb.append((char) ('a' + move.getFrom().getCol()));
            }
            sb.append("x");
        }

        sb.append(positionToString(move.getTo()));

        return sb.toString();
    }

    private static String positionToString(Position pos) {
        char file = (char) ('a' + pos.getCol());
        int rank = pos.getRow() + 1;
        return "" + file + rank;
    }
}
