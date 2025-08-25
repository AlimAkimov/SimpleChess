package org.example.Model;

import org.example.Model.ChessFigures.*;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private static final int BOARD_SIZE = 8;
    private final Figure[][] board = new Figure[BOARD_SIZE][BOARD_SIZE];

    public BoardImpl() {
    }

    public Figure getFigure(Position pos) {
        return board[pos.getCol()][pos.getRow()];
    }

    public boolean isEmpty(Position position) {
        return getFigure(position) == null;
    }

    public void setup() {
        for (int col = 0; col < BOARD_SIZE; col++) {
            board[col][1] = new Pawn(Color.WHITE, new Position(col, 1));// a2-h2
        }
        board[0][0] = new Rook(Color.WHITE, new Position(0, 0));   // a1
        board[1][0] = new Knight(Color.WHITE, new Position(1, 0)); // b1
        board[2][0] = new Bishop(Color.WHITE, new Position(2, 0)); // c1
        board[3][0] = new Queen(Color.WHITE, new Position(3, 0));  // d1
        board[4][0] = new King(Color.WHITE, new Position(4, 0));   // e1
        board[5][0] = new Bishop(Color.WHITE, new Position(5, 0)); // f1
        board[6][0] = new Knight(Color.WHITE, new Position(6, 0)); // g1
        board[7][0] = new Rook(Color.WHITE, new Position(7, 0));   // h1


        for (int col = 0; col < 8; col++) {
            board[col][6] = new Pawn(Color.BLACK, new Position(col, 6)); // a7-h7
        }
        board[0][7] = new Rook(Color.BLACK, new Position(0, 7));   // a8
        board[1][7] = new Knight(Color.BLACK, new Position(1, 7)); // b8
        board[2][7] = new Bishop(Color.BLACK, new Position(2, 7)); // c8
        board[3][7] = new Queen(Color.BLACK, new Position(3, 7));  // d8
        board[4][7] = new King(Color.BLACK, new Position(4, 7));   // e8
        board[5][7] = new Bishop(Color.BLACK, new Position(5, 7)); // f8
        board[6][7] = new Knight(Color.BLACK, new Position(6, 7)); // g8
        board[7][7] = new Rook(Color.BLACK, new Position(7, 7));   // h8

    }

    public void move(Move move) {
        Position from = move.getFrom();
        Position to = move.getTo();
        Figure figure = getFigure(from);
        if (figure != null) {
            board[to.getCol()][to.getRow()] = figure;
            board[from.getCol()][from.getRow()] = null;
            figure.moveTo(to);
            if (figure instanceof Pawn pawn) {
                pawn.setHasMoved(true);
            }
        }
    }

    public List<Figure> getFigures(Color color) {
        List<Figure> figures = new ArrayList<>();
        for (int col = 0; col < BOARD_SIZE; col++) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                Figure figure = board[col][row];
                if (figure != null && figure.getColor() == color) {
                    figures.add(figure);
                }
            }
        }
        return figures;
    }

    @Override
    public boolean positionIsUnderAttack(Position pos, Color byColor) {
        List<Figure> figures = getFigures(byColor);
        for (Figure figure : figures) {
            List<Position> attacks = figure.getAttackCells(this);
            if (attacks.contains(pos)) {
                return true;
            }
        }
        return false;
    }


}

