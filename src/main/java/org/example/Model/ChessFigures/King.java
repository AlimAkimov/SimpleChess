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

        if (!Position.isOnBoard(newCol, newRow)) {
            return;
        }
        Position newPosition = new Position(newCol, newRow);
        Figure figureOnTarget = board.getFigure(newPosition);

        if (figureOnTarget != null && !isEnemy(figureOnTarget)) {
            return;
        }
        Color enemyColor;
        if (getColor() == Color.WHITE) {
            enemyColor = Color.BLACK;
        } else {
            enemyColor = Color.WHITE;
        }
        if (board.positionIsUnderAttack(newPosition, enemyColor)) {
            return;
        }

        moves.add(new Move(currentPosition, newPosition));
    }

    public List<Position> getAttackCells(Board board) {
        List<Position> attacks = new ArrayList<>();
        Position currentPosition = getPosition();

        addKingAttack(attacks, currentPosition, 1, 0);   // вправо
        addKingAttack(attacks, currentPosition, -1, 0);  // влево
        addKingAttack(attacks, currentPosition, 0, 1);   // вверх
        addKingAttack(attacks, currentPosition, 0, -1);  // вниз
        addKingAttack(attacks, currentPosition, 1, 1);   // вправо-вверх
        addKingAttack(attacks, currentPosition, 1, -1);  // вправо-вниз
        addKingAttack(attacks, currentPosition, -1, 1);  // влево-вверх
        addKingAttack(attacks, currentPosition, -1, -1); // влево-вниз

        return attacks;
    }

    private void addKingAttack(List<Position> attacks, Position currentPosition, int col, int row) {
        int newCol = currentPosition.getCol() + col;
        int newRow = currentPosition.getRow() + row;

        if (Position.isOnBoard(newCol, newRow)) {
            attacks.add(new Position(newCol, newRow));
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
