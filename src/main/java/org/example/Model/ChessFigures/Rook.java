package org.example.Model.ChessFigures;

import org.example.Model.*;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Figure {

    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position currentPosition = getPosition();

        addSlidingMoves(board, moves, currentPosition, 1, 0);   // Вправо
        addSlidingMoves(board, moves, currentPosition, -1, 0);  // Влево
        addSlidingMoves(board, moves, currentPosition, 0, 1);   // Вверх
        addSlidingMoves(board, moves, currentPosition, 0, -1);  // Вниз

        return moves;
    }

    @Override
    public char getSymbol() {
        if (getColor() == Color.WHITE) {
            return 'R';
        } else
            return 'r';
    }

    @Override
    public String getName() {
        return "Rook";
    }


}

