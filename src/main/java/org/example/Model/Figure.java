package org.example.Model;

import java.util.List;

public abstract class Figure {
    private final Color color;
    private Position position;

    public Figure(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public abstract List<Move> getPossibleMoves(Board board);


    public boolean isEnemy(Figure other) {
        return other != null && other.getColor() != this.getColor();
    }

    public abstract char getSymbol();

    public void moveTo(Position newPos) {
        this.position = newPos;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public abstract String getName();

    protected void addSlidingMoves(Board board, List<Move> moves, Position currentPosition, int col, int row) {
        for (int i = 1; i <= 7; i++) {
            int newCol = currentPosition.getCol() + i * col;
            int newRow = currentPosition.getRow() + i * row;
            if (Position.isOnBoard(newCol, newRow)) {
                Position validPosition = new Position(newCol, newRow);
                if (board.isEmpty(validPosition)) {
                    moves.add(new Move(currentPosition, validPosition));
                } else {
                    Figure opponentsFigure = board.getFigure(validPosition);
                    if (isEnemy(opponentsFigure)) {
                        moves.add(new Move(currentPosition, validPosition));
                    }
                    break;
                }
            } else {
                break;
            }
        }
    }

    protected void addSlidingAttacks(Board board, List<Position> attacks, Position currentPosition, int col, int row) {
        for (int i = 1; i <= 7; i++) {
            int newCol = currentPosition.getCol() + i * col;
            int newRow = currentPosition.getRow() + i * row;
            if (!Position.isOnBoard(newCol, newRow)) {
                break;
            }
            attacks.add(new Position(newCol, newRow));
            if (!board.isEmpty(new Position(newCol, newRow))) {
                break;
            }
        }
    }
    public abstract List<Position> getAttackCells(Board board);
}
