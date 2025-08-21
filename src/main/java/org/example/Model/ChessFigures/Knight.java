package org.example.Model.ChessFigures;

import org.example.Model.*;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Figure {


    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position currentPosition = getPosition();

        addKnightMoves(board, moves, currentPosition, 2, 1);   // Вправо-вверх
        addKnightMoves(board, moves, currentPosition, 2, -1);  // Вправо-вниз
        addKnightMoves(board, moves, currentPosition, -2, 1);  // Влево-вверх
        addKnightMoves(board, moves, currentPosition, -2, -1); // Влево-вниз
        addKnightMoves(board, moves, currentPosition, 1, 2);   // Вправо-вверх (два вверх)
        addKnightMoves(board, moves, currentPosition, 1, -2);  // Вправо-вниз (два вниз)
        addKnightMoves(board, moves, currentPosition, -1, 2);  // Влево-вверх (два вверх)
        addKnightMoves(board, moves, currentPosition, -1, -2); // Влево-вниз (два вниз)

        return moves;
    }

    private void addKnightMoves(Board board, List<Move> moves, Position currentPosition, int col, int row) {
        int newCol = currentPosition.getCol() + col;
        int newRow = currentPosition.getRow() + row;
        if (Position.isValidPosition(newCol, newRow)) {
            Position validPosition = new Position(newCol, newRow);
            if (board.isEmpty(validPosition)) {
                moves.add(new Move(currentPosition, validPosition));
            } else {
                Figure opponentsFigure = board.getFigure(validPosition);
                if (isEnemy(opponentsFigure)) {
                    moves.add(new Move(currentPosition, validPosition));
                }
            }
        }
    }

    @Override
    public char getSymbol() {
        if (getColor() == Color.WHITE) {
            return 'N';
        } else
            return 'n';
    }

    @Override
    public String getName() {
        return "Knight";
    }

}
