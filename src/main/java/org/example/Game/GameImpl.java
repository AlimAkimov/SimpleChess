package org.example.Game;

import org.example.Model.*;
import org.example.Model.ChessFigures.King;
import org.example.PGN.PgnFormatter;
import org.example.PGN.PgnMove;
import org.example.PrintBoardInConsole.PrintBoard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameImpl implements Game{
    private Board board;
    private Color currentPlayer;
    private int moveCount;
    private PrintBoard print;
    private boolean hasMoved;
    private List<PgnMove> moves;

    public GameImpl() {
        board = new BoardImpl();
        board.setup();
        currentPlayer = Color.WHITE;
        moveCount = 0;
        print = new PrintBoard();
        this.moves = new ArrayList<>();
    }

    private boolean hasMoves(Color color) {
        List<Figure> figures = board.getFigures(color);
        for (Figure figure : figures) {
            if (!figure.getPossibleMoves(board).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void switchPlayer() {
        if (currentPlayer == Color.WHITE) {
            currentPlayer = Color.BLACK;
        } else {
            currentPlayer = Color.WHITE;
        }
    }

    private boolean isKingAlive(Color color) {
        List<Figure> figures = board.getFigures(color);
        for (Figure figure : figures) {
            if (figure instanceof King) {
                return true;
            }
        }
        return false;
    }

    private void makeRandomMove() {
        List<Figure> figures = board.getFigures(currentPlayer);
        Collections.shuffle(figures, new Random());
        for (Figure figure : figures) {
            List<Move> possibleMoves = figure.getPossibleMoves(board);
            if (!possibleMoves.isEmpty()) {
                Move move = possibleMoves.get(new Random().nextInt(possibleMoves.size()));
                System.out.println(figure.getColor() + " " + figure.getName() + " Ход: " + move);
                Figure target = board.getFigure(move.getTo());
                boolean capture = (target != null);
                board.move(move);
                PgnMove pgnMove = new PgnMove(figure, move.getFrom(), move.getTo(), capture);
                moves.add(pgnMove);
                moveCount++;
                hasMoved = true;
                return;
            }
        }
        hasMoved = false;
    }

    public void start() {
        while (true) {
            if (!isKingAlive(Color.WHITE) || !isKingAlive(Color.BLACK)) {
                break;
            }
            if (!hasMoves(currentPlayer)) {
                break;
            }
            System.out.println(currentPlayer + " ходят");
            print.printBoard(board);
            makeRandomMove();
            if (hasMoved) {
                switchPlayer();
            } else {
                break;
            }
        }

        String message;
        if (!isKingAlive(Color.WHITE)) {
            message = "Игра окончена, белый король пал. Победили чёрные.";
        } else if (!isKingAlive(Color.BLACK)) {
            message = "Игра окончена, чёрный король пал. Победили белые.";
        } else {
            message = "Игра окончена, нет ходов у " + currentPlayer + ".";
        }
        System.out.println(message + " Всего было ходов: " + moveCount);
        print.printBoard(board);

        printGame();
    }

    private void printGame() {
        System.out.println("\nPGN запись:");
        for (int i = 0; i < moves.size(); i++) {
            if (i % 2 == 0) {
                int turn = i / 2 + 1;
                System.out.print(turn + ". ");
            }
            System.out.print(PgnFormatter.format(moves.get(i)) + " ");
        }
        System.out.println();
    }
}
