package org.example.Model.ChessFigures;

import org.example.Model.*;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Figure {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position currentPosition = getPosition();

        // Диагональные направления
        addSlidingMoves(board, moves, currentPosition, 1, 1);   // Вправо-вверх
        addSlidingMoves(board, moves, currentPosition, 1, -1);  // Вправо-вниз
        addSlidingMoves(board, moves, currentPosition, -1, 1);  // Влево-вверх
        addSlidingMoves(board, moves, currentPosition, -1, -1); // Влево-вниз

        return moves;
    }

    @Override
    public List<Position> getAttackCells(Board board) {
        List<Position> attacks = new ArrayList<>();
        Position currentPosition = getPosition();

        addSlidingAttacks(board, attacks, currentPosition, 1, 1);
        addSlidingAttacks(board, attacks, currentPosition, 1, -1);
        addSlidingAttacks(board, attacks, currentPosition, -1, 1);
        addSlidingAttacks(board, attacks, currentPosition, -1, -1);

        return attacks;
    }

    @Override
    public char getSymbol() {
        if (getColor() == Color.WHITE) {
            return 'B';
        } else
            return 'b';
    }

    @Override
    public String getName() {
        return "Bishop";
    }
}
