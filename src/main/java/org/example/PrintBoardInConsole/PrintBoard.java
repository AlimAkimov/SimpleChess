package org.example.PrintBoardInConsole;

import org.example.Model.Board;
import org.example.Model.BoardImpl;
import org.example.Model.Figure;
import org.example.Model.Position;

public class PrintBoard {

    public void printBoard(Board board) {
        System.out.println("  a b c d e f g h");
        System.out.println("+-----------------+");
        for (int row = 7; row >= 0; row--) {
            System.out.print((row + 1) + "|");
            for (int col = 0; col < 8; col++) {
                Figure figure = board.getFigure(new Position(col, row));
                if (figure != null) {
                    System.out.print(figure.getSymbol() + " ");
                } else {
                    boolean isLightSquare = (row + col) % 2 == 0;
                    System.out.print(isLightSquare ? "□ " : "■ ");
                }
            }
            System.out.println("|" + (row + 1));
        }
        System.out.println("+-----------------+");
        System.out.println("  a b c d e f g h");
    }
}
