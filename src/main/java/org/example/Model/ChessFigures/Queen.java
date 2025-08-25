package org.example.Model.ChessFigures;

import org.example.Model.*;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Figure {

    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position currentPosition = getPosition();

        // по горизонтали и вертикали (как ладья)
        addSlidingMoves(board, moves, currentPosition, 1, 0);   // Вправо
        addSlidingMoves(board, moves, currentPosition, -1, 0);  // Влево
        addSlidingMoves(board, moves, currentPosition, 0, 1);   // Вверх
        addSlidingMoves(board, moves, currentPosition, 0, -1);  // Вниз
        // по диагонали (как слон)
        addSlidingMoves(board, moves, currentPosition, 1, 1);   // Вправо-вверх
        addSlidingMoves(board, moves, currentPosition, 1, -1);  // Вправо-вниз
        addSlidingMoves(board, moves, currentPosition, -1, 1);  // Влево-вверх
        addSlidingMoves(board, moves, currentPosition, -1, -1); // Влево-вниз

        return moves;
    }

    @Override
    public char getSymbol() {
        if (getColor() == Color.WHITE) {
            return 'Q';
        } else
            return 'q';
    }
    @Override
    public String getName() {
        return "Queen";
    }

    @Override
    public List<Position> getAttackCells(Board board) {
        return List.of();
    }
}

