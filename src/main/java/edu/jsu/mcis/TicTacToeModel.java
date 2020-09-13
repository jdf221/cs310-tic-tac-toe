package edu.jsu.mcis;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TicTacToeModel {

    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */

    /* ENUM TYPE DEFINITIONS */

    /* Mark (represents X, O, or an empty square */

    public enum Mark {

        X("X"),
        O("O"),
        EMPTY("-");

        private String message;

        private Mark(String msg) {
            message = msg;
        }

        @Override
        public String toString() {
            return message;
        }

    }

    ;
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */

    public enum Result {

        X("X"),
        O("O"),
        TIE("TIE"),
        NONE("NONE");

        private String message;

        private Result(String msg) {
            message = msg;
        }

        @Override
        public String toString() {
            return message;
        }

    }

    ;

    /* CONSTRUCTOR */

    public TicTacToeModel() {

        this(TicTacToe.DEFAULT_WIDTH);

    }

    /* CONSTRUCTOR */

    public TicTacToeModel(int width) {

        /* Initialize width; X goes first */

        this.width = width;
        xTurn = true;

        /* Create board (width x width) as a 2D Mark array */

        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */

        for (int row = 0; row < board.length; row++) {
            Arrays.fill(board[row], Mark.EMPTY);
        }

    }

    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */

        if (isValidSquare(row, col) && !isSquareMarked(row, col)) {
            board[row][col] = xTurn ? Mark.X : Mark.O;
            xTurn = !xTurn;
            return true;
        } else {
            return false;
        }

    }

    private boolean isValidSquare(int row, int col) {

        /* Return TRUE if the specified location is within the bounds of the board */

        if ((row >= 0 && row < width) && (col >= 0 && col < width)) {
            return true;
        } else {
            return false;
        }

    }

    private boolean isSquareMarked(int row, int col) {

        /* Return TRUE if the square at specified location is marked */

        return board[row][col] != Mark.EMPTY;

    }

    public Mark getMark(int row, int col) {

        /* Return the mark from the square at the specified location */

        return board[row][col];

    }

    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */

        if (isMarkWin(Mark.O)) {
            return Result.O;
        } else if (isMarkWin(Mark.X)) {
            return Result.X;
        } else if (isTie()) {
            return Result.TIE;
        } else {
            return Result.NONE;
        }

    }

    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */

        int diagCount = 0;
        int antiDiagCount = 0;
        for (int row = 0; row < width; row++) {
            int rowCount = 0;
            int colCount = 0;

            for (int column = 0; column < width; column++) {
                if (board[row][column] == mark) {
                    rowCount++;
                }

                if (board[column][row] == mark) {
                    colCount++;
                }
            }

            if (rowCount == width || colCount == width) {
                return true;
            }

            if (board[row][row] == mark) {
                diagCount++;
            }
            if (board[width - row - 1][row] == mark) {
                antiDiagCount++;
            }
        }

        if (diagCount == width || antiDiagCount == width) {
            return true;
        }

        return false;
    }

    private boolean isTie() {

        /* Check the squares of the board to see if the game is a tie */

        for (int row = 0; row < width; row++) {
            for (int column = 0; column < width; column++) {
                if (board[row][column] == Mark.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isGameover() {

        /* Return TRUE if the game is over */

        return (Result.NONE != getResult());

    }

    public boolean isXTurn() {

        /* Getter for xTurn */

        return xTurn;

    }

    public int getWidth() {

        /* Getter for width */

        return width;

    }

    @Override
    public String toString() {

        StringBuilder output = new StringBuilder("  ");

        /* Output the board contents as a string (see examples) */

        output.append(String.join("", IntStream.rangeClosed(0, width - 1).mapToObj(String::valueOf).toArray(String[]::new)));
        output.append("\n");

        for (int row = 0; row < board.length; row++) {
            output.append("\n" + String.valueOf(row) + " ");
            for (int column = 0; column < board[row].length; column++) {
                output.append(board[row][column].toString());
            }
        }

        output.append("\n");

        return output.toString();

    }

}
