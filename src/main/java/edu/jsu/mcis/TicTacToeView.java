package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToeView {

    private final Scanner keyboard;

    /* CONSTRUCTOR */

    public TicTacToeView() {

        /* Initialize scanner (for console keyboard) */

        keyboard = new Scanner(System.in);

    }

    public TicTacToeMove getNextMove(boolean isXTurn) {
        
        /* Prompt the player to enter the row and the column of their next move.
           Return as a TicTacToeMove object. */

        if (isXTurn) {
            System.out.println("Player 1 (X) Move:");
        } else {
            System.out.println("Player 2 (O) Move:");
        }
        System.out.print("Enter the row and column numbers, separated by a space: ");
        String[] userInput = keyboard.nextLine().split(" ");

        return new TicTacToeMove(Integer.parseInt(userInput[0]), Integer.parseInt(userInput[1]));

    }

    public void showInputError() {

        System.out.println("Entered location is invalid, already marked, or out of bounds.");

    }

    public void showResult(String r) {

        System.out.println(r + "!");

    }

    public void showBoard(String board) {

        System.out.println("\n\n" + board);

    }

}
