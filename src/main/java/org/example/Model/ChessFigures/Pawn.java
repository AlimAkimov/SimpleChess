package org.example.Model.ChessFigures;

import org.example.Model.*;

import java.util.ArrayList;
import java.util.List;


public class Pawn extends Figure {
    private boolean hasMoved = false;

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        List<Move> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getCol();
        int direction = (getColor() == Color.WHITE) ? 1 : -1;

        //ход на 1 клетку вперед
        int newRow = row + direction;
        if (Position.isOnBoard(col, newRow) && board.isEmpty(new Position(col, newRow))) {
            moves.add(new Move(getPosition(), new Position(col, newRow)));
        }
        //2 клетки
        if (!hasMoved) {
            newRow = row + 2 * direction;
            if (Position.isOnBoard(col, newRow) &&
                    board.isEmpty(new Position(col, newRow)) &&
                    board.isEmpty(new Position(col, row + direction))) {
                moves.add(new Move(getPosition(), new Position(col, newRow)));
            }
        }
        //съесть фигуру по диагонали
        int[] capture = {-1, 1};
        for (int cap : capture) {
            newRow = row + direction;
            int newCol = col + cap;
            if (Position.isOnBoard(newCol, newRow)) {
                Figure opponentsFigure = board.getFigure(new Position(newCol, newRow));
                if (opponentsFigure != null && isEnemy(opponentsFigure)) {
                    moves.add(new Move(getPosition(), new Position(newCol, newRow)));
                }
            }
        }
        return moves;
    }

    @Override
    public List<Position> getAttackCells(Board board) {
        List<Position> attacks = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getCol();
        int direction = (getColor() == Color.WHITE) ? 1 : -1;

        int[] captureCols = {-1, 1}; // Диагонали
        for (int dCol : captureCols) {
            int newCol = col + dCol;
            int newRow = row + direction;
            if (Position.isOnBoard(newCol, newRow)) {
                attacks.add(new Position(newCol, newRow));
            }
        }
        return attacks;
    }

    @Override
    public char getSymbol() {
        if (getColor() == Color.WHITE) {
            return 'P';
        } else
            return 'p';
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public String getName() {
        return "Pawn";
    }


}
