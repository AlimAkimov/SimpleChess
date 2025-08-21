package org.example;

import org.example.Game.Game;
import org.example.Game.GameImpl;

public class Main {
    public static void main(String[] args) {
        Game game = new GameImpl();
        game.start();
    }
}