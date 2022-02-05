package tictactoe;

import java.util.*;

// TODO Naming conventions | Work on naming not to name them regarding the topic that they cover, name them regarding what the player can expect them to do

enum Player {
    HUMAN("X"),
    COMPUTER("O");

    private final String symbol;

    Player(String symbol) {
        this.symbol = symbol;
    }

    String getSymbol() {
        return symbol;
    }

}

public class TicTacToe {

    private final Player[] grid = new Player[ROWS * COLS];
    private final Scanner input = new Scanner(System.in);
    private static final int COLS = 3;
    private static final int ROWS = 3;

    public TicTacToe() {
        displayGrid();
    }

    public void start() {
        gameLoop();
    }

    private void gameLoop() {
        while (hasWinner()) {
            runHumansTurn();
            if (isDraw()) {
                System.out.println("DRAW");
                return;
            }
            runComputerTurn();
            displayGrid();
        }
        getWinner();
    }

    private boolean hasWinner() {
        if (isWinner(Player.HUMAN)) {
            return false;
        } else return !isWinner(Player.COMPUTER);
    }

    private void runHumansTurn() {
        System.out.println("Pick a number from 1 - 9 ");
        int humanSelectedIndex = input.nextInt();
        while (isTileTaken(grid[humanSelectedIndex - 1])) {
            System.out.println("Try again that tile is taken");
            humanSelectedIndex = input.nextInt();
        }
        grid[humanSelectedIndex - 1] = Player.HUMAN;
    }

    private boolean isDraw() {
        return Arrays.stream(grid).distinct().count() == 2 && !hasWinner();
    }

    private void runComputerTurn() {
        int computerSelectedIndex = (int) (Math.random() * 9 + 1);
        while (isTileTaken(grid[computerSelectedIndex - 1])) {
            computerSelectedIndex = (int) (Math.random() * 9 + 1);
        }
        grid[computerSelectedIndex - 1] = Player.COMPUTER;
    }

    private void displayGrid() {
        for (int i = 0; i < grid.length; i++) {
            String symbolText = grid[i] == null ? " " : grid[i].getSymbol();

            String delimiter = isLastRowIndex(i) ? "\n" : "|";

            System.out.print(symbolText + delimiter);
        }
    }

    private void getWinner() {
        if (isWinner(Player.HUMAN)) {
            System.out.println("Player won");
        } else {
            System.out.println("Computer won");
        }
    }

    private boolean isTileTaken(Player playerTile) {
        return playerTile != null;
    }

    private boolean isLastRowIndex(int index) {
        return index == 2 || index == 5 || index == 8;
    }

    private boolean isWinner(Player userCharacter) {
        return hasWonHorizontally(userCharacter)
                || hasWonVertically(userCharacter)
                || hasWonDiagonally(userCharacter);
    }

    private boolean hasWonDiagonally(Player userCharacter) {
        return leftToRightDiagonalWin(userCharacter) || rightToLeftDiagonalWin(userCharacter);
    }

    private boolean rightToLeftDiagonalWin(Player userCharacter) {
        for (int rows = 0; rows < ROWS; rows++) {
            Player tile = grid[translateIndex(COLS - rows -1, rows)];
            if (tile != userCharacter) {
                return false;
            }
        }
        return true;
    }

    private boolean leftToRightDiagonalWin(Player userCharacter) {
        for (int i = 0; i < ROWS; i++) {
            Player tile = grid[translateIndex(i, i)];
            if (tile != userCharacter) {
                return false;
            }
        }
        return true;
    }

    private boolean hasWonVertically(Player userCharacter) {
        for (int col = 0; col < COLS; col++) {
            boolean isColWon = true;
            for (int row = 0; row < ROWS; row++) {
                int tileIndex = translateIndex(row, col);
                if (grid[tileIndex] != userCharacter) {
                    isColWon = false;
                    break;
                }
            }

            if (isColWon) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWonHorizontally(Player userCharacter) {
        for (int row = 0; row < ROWS; row++) {
            boolean isRowWon = true;
            for (int col = 0; col < COLS; col++) {
                int tileIndex = translateIndex(row, col);
                if (grid[tileIndex] != userCharacter) {
                    isRowWon = false;
                    break;
                }
            }
            if (isRowWon) {
                return true;
            }
        }
        return false;
    }

    private int translateIndex(int row, int col) {
        return row * COLS + col;
    }
}