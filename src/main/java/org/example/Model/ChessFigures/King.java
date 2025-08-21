package org.example.Model.ChessFigures;

import org.example.Model.*;

import java.util.ArrayList;
import java.util.List;

public class King extends Figure {

    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        Position currentPosition = getPosition();

        addKingMoves(board, moves, currentPosition, 1, 0);   // Вправо
        addKingMoves(board, moves, currentPosition, -1, 0);  // Влево
        addKingMoves(board, moves, currentPosition, 0, 1);   // Вверх
        addKingMoves(board, moves, currentPosition, 0, -1);  // Вниз
        addKingMoves(board, moves, currentPosition, 1, 1);   // Вправо-вверх
        addKingMoves(board, moves, currentPosition, 1, -1);  // Вправо-вниз
        addKingMoves(board, moves, currentPosition, -1, 1);  // Влево-вверх
        addKingMoves(board, moves, currentPosition, -1, -1); // Влево-вниз

        return moves;
    }
    private void addKingMoves(Board board, List<Move> moves, Position currentPosition, int col, int row) {
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
            return 'K';
        } else
            return 'k';
    }

    @Override
    public String getName() {
        return "King";
    }


}
